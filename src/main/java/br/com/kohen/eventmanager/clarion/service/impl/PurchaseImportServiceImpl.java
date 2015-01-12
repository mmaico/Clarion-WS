package br.com.kohen.eventmanager.clarion.service.impl;

import static br.com.kohen.eventmanager.commons.entity.builder.EventBuilder.createEvent;

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
import br.com.kohen.eventmanager.commons.entity.Company;
import br.com.kohen.eventmanager.commons.entity.Purchase;
import br.com.kohen.eventmanager.commons.entity.PurchaseItem;
import br.com.kohen.eventmanager.commons.service.CommonCompanyService;

@Component
@Transactional
public class PurchaseImportServiceImpl implements PurchaseImportService {

	private Log log = LogFactory.getLog(PurchaseImportServiceImpl.class);
	private static final Long ID_TEST_CATEGORY = 20l;
	private static final Long DEFAULT_EVENT = 1l;
	private static Boolean running = false;
	
	private @Autowired PurchaseValidator validator;
	
	private @Autowired LogService logService;
	private @Autowired PurchaseWsService purchaseWsService;
	
	@Autowired
	private ClarionPurchaseRepository clarionPurchaseDAO;
	
	@Autowired
	@Qualifier("commonCompanyService")
	private CommonCompanyService companyService;
	
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
					if (isValidImport(purchase)) {
						importPurchase(purchase);
					}
				} catch(Exception e){
					log.error("########################## Erro no processo de envio da compra: " + purchase.getId() +" --" + e.getMessage());
				}	
			}
			
		} finally {
			running = false;
		}
		
	}
	
	public Boolean isValidImport(Purchase purchase) {
		Company responsible = purchase.getResponsible();
		
		if (responsible.isManExhibitor()) {
			if (ID_TEST_CATEGORY.equals(responsible.getCompanyCategory().getId())) {
				return Boolean.FALSE;
			}
			
			return Boolean.TRUE;
		} else {
			Company represented = companyService.getCompanyRepresented(responsible, createEvent(DEFAULT_EVENT).build());
			if (represented != null) {
				if (ID_TEST_CATEGORY.equals(responsible.getCompanyCategory().getId())) {
					return Boolean.FALSE;
				}
			}
			return Boolean.TRUE;
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
