package br.com.kohen.eventmanager.clarion.service;

import br.com.kohen.eventmanager.clarion.ws.entity.ImportError;
import br.com.kohen.eventmanager.clarion.ws.entity.ImportPurchaseError;
import br.com.kohen.eventmanager.commons.entity.Company;
import br.com.kohen.eventmanager.commons.entity.Purchase;

public interface LogService {

	void saveCompanyError(ImportError error);
	
	void savePurchaseError(ImportPurchaseError error);
	
	void deleteError(Purchase purchase);
	
	void deleteError(Company company);
}
