package br.com.kohen.eventmanager.clarion.service;

import br.com.kohen.eventmanager.commons.entity.Purchase;

public interface PurchaseImportService {

	
	void processPurchases();
	
	void importPurchase(Purchase purchase);
}
