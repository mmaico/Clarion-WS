package br.com.kohen.eventmanager.clarion.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.kohen.eventmanager.clarion.repository.ClarionPurchaseRepository;
import br.com.kohen.eventmanager.clarion.service.LogService;
import br.com.kohen.eventmanager.clarion.service.PurchaseImportService;
import br.com.kohen.eventmanager.clarion.ws.service.PurchaseWsService;
import br.com.kohen.eventmanager.clarion.ws.validator.PurchaseValidator;
import br.com.kohen.eventmanager.commons.config.PropertiesAcessor;
import br.com.kohen.eventmanager.commons.entity.Purchase;
import br.com.kohen.eventmanager.commons.entity.PurchaseItem;

@Component
@Transactional
public class PurchaseImportServiceImpl implements PurchaseImportService {

	private Log log = LogFactory.getLog(PurchaseImportServiceImpl.class);
	
	private static Boolean running = false;
	
	private @Autowired PurchaseValidator validator;
	
	private @Autowired LogService logService;
	private @Autowired PurchaseWsService purchaseWsService;
	
	@Autowired
	@Qualifier("clarionPurchaseDAO")
	private ClarionPurchaseRepository clarionPurchaseDAO;
	
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
		
		if (enabled == null || !enabled) {
			log.debug("########################## Importacao desativada");
			return;
		}	
		
		sortItems(purchase.getPurchseItems());
		
		this.purchaseWsService.sendToWs(purchase);
		
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
	
}
