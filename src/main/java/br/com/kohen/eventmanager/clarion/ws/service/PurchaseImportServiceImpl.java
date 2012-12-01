package br.com.kohen.eventmanager.clarion.ws.service;

import static br.com.kohen.eventmanager.commons.utils.StringUtils.isNullOrEmpty;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.kohen.eventmanager.clarion.dao.ClarionPurchaseDAO;
import br.com.kohen.eventmanager.clarion.ws.dao.PurchaseImportDAO;
import br.com.kohen.eventmanager.clarion.ws.entity.ImportPurchaseError;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.APEDIDO;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.ARRAYOFDADOSITENS;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.DADOSITENS;
import br.com.kohen.eventmanager.commons.config.PropertiesAcessor;
import br.com.kohen.eventmanager.commons.dao.CommonBaseDAO;
import br.com.kohen.eventmanager.commons.entity.Company;
import br.com.kohen.eventmanager.commons.entity.Purchase;
import br.com.kohen.eventmanager.commons.entity.PurchaseItem;
import br.com.kohen.eventmanager.commons.enums.Language;

@Component
@Transactional
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PurchaseImportServiceImpl {

	private Log log = LogFactory.getLog(PurchaseImportServiceImpl.class);
	
	private static Boolean running = false;
	
	@Autowired(required=false)
	private PurchaseImportDAO purchaseImportDAO;
	
	@Autowired
	@Qualifier("commonBaseDAO")
	private CommonBaseDAO baseDAO;
	
	@Autowired
	@Qualifier("clarionPurchaseDAO")
	private ClarionPurchaseDAO clarionPurchaseDAO;
	
	private PropertiesAcessor properties = new PropertiesAcessor();
	
	
	@Scheduled(fixedDelay=60000)
	public void processPurchases() {
		
		try {
			
			if (running)
				return;
			
			running = true;
			
			Boolean enabled = properties.loadSystemProperty().get("import.clarion.enabled", Boolean.class);
			
			if (enabled == null || !enabled)
				return;
			
			List<Purchase> list = clarionPurchaseDAO.getAllPurchaseNotImported();
			log.debug("########################## Compras a ser importadas: " + list.size());
			
			for (Purchase purchase : list) {
				try {
					importPurchase(purchase);
				} catch(Exception e){
					log.error("########################## Erro no processo de envio da compra: " + purchase.getId() +" --" + e.getMessage());
				}	
			}
			
		} finally {
			running = false;
		}
		
	}
	
	public void importPurchase(Purchase purchase) {
		
		
		Boolean enabled = properties.loadSystemProperty().get("import.clarion.enabled", Boolean.class);
		InfoExport infoExport = new InfoExport();
		
		if (enabled == null || !enabled || !infoExport.isValid()) {
			log.debug("########################## Importacao desativada ou configuracoes invalidas!");
			return;
		}	
		
		APEDIDO pedido = APEDIDO.build(infoExport, purchase);
		
		Company responsible = purchase.getResponsible();
		if (responsible.getCode() == null) {
			String message = "O responsavel pelo pagamento ainda nao tem o codigo protheus, nome: " + responsible.getName() + "-- codigo bluebox:" + responsible.getId();
			log.debug(message);
			
			ImportPurchaseError error = ImportPurchaseError.internalError(message).purchaseId(purchase.getId());
			saveErrorInDatabase(error);
			return;
		}
		
		pedido.setCODPROTHEUS(responsible.getCode());
		
		ARRAYOFDADOSITENS arrayDados = new ARRAYOFDADOSITENS();
		
		List<PurchaseItem> purchseItems = purchase.getPurchseItems();
		sortItems(purchseItems);
		
		for (PurchaseItem purchaseItem : purchseItems) {
			
			if (purchaseItem.getProduct().getExternalCode() == null) {
				String message = "Existe produto na venda sem o codigo protheus, nome:" + purchaseItem.getProduct().getName().get(Language.PT) + "-- codigo bluebox:" + purchaseItem.getProduct().getId();
				log.debug(message);
				ImportPurchaseError error = ImportPurchaseError.internalError(message).purchaseId(purchase.getId());
				saveErrorInDatabase(error);
				return;
			}
			
			arrayDados.getDADOSITENS().add(DADOSITENS.build(purchaseItem, infoExport));
			
		}
		
		pedido.setITENS(arrayDados);
		
		String importPurchase = purchaseImportDAO.importPurchase(pedido);
		
		if (!NumberUtils.isNumber(importPurchase)) {
			String message = "O pedido de numero : " + purchase.getId() + " Responsavel pelo pagamento: " + purchase.getResponsible().getName() + " sofreu um erro: " + importPurchase;
			log.debug(message);
			ImportPurchaseError error = ImportPurchaseError.exError(message).purchaseId(purchase.getId());
			saveErrorInDatabase(error);
			return;
		}
		
		purchase.setCode(importPurchase);
		ImportPurchaseError errorImport = (ImportPurchaseError) baseDAO.findById(ImportPurchaseError.class, purchase.getId());
		
		if (errorImport != null)
			baseDAO.delete(errorImport);
		
		baseDAO.saveOrUpdate(purchase);
		
	}

	private void saveErrorInDatabase(ImportPurchaseError importError) {
		ImportPurchaseError error = (ImportPurchaseError) clarionPurchaseDAO.getPurchaseErrorById(importError.getId());
		
		if (error != null) {
			error.setCause(importError.getCause());
			error.setCauseEx(importError.getCauseEx());
			error.setDate(new Date());
			baseDAO.saveOrUpdate(error);
		} else {
			baseDAO.saveOrUpdate(importError);
		}
		
	}
	
	public void sortItems(List<PurchaseItem> purchseItems) {
		Collections.sort(purchseItems, new Comparator<PurchaseItem>() {

			@Override
			public int compare(PurchaseItem item1, PurchaseItem itemTwo) {
				
				BigDecimal totalItem1 = item1.getPrice().multiply(new BigDecimal(item1.getQtd()));
				BigDecimal totalItemTwo = itemTwo.getPrice().multiply(new BigDecimal(itemTwo.getQtd()));
				
				return totalItemTwo.compareTo(totalItem1);
			}
			
		});
	}
	
	
	public class InfoExport {
		
		public String chave, custo, reciss, tes;
		
		public InfoExport() {
			chave = "ABC1000";
			custo = properties.loadSystemProperty().get("import.clarion.centro.custo", String.class);
			reciss = properties.loadSystemProperty().get("import.clarion.client.sp", String.class);
			tes =  properties.loadSystemProperty().get("import.clarion.codigo.tes", String.class);
		}
	
		public Boolean isValid() {
			
			if (isNullOrEmpty(custo)) return false;
			
			if (isNullOrEmpty(reciss)) return false;
			
			if (isNullOrEmpty(tes)) return false;
			
			return true;
		}
		
	}
	
	
}
