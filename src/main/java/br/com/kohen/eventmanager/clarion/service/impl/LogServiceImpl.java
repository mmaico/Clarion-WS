package br.com.kohen.eventmanager.clarion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.kohen.eventmanager.clarion.repository.ClarionCompanyImportErrorRepository;
import br.com.kohen.eventmanager.clarion.repository.ClarionCompanyRepository;
import br.com.kohen.eventmanager.clarion.repository.ClarionPurchaseImportErrorRepository;
import br.com.kohen.eventmanager.clarion.repository.ClarionPurchaseRepository;
import br.com.kohen.eventmanager.clarion.service.LogService;
import br.com.kohen.eventmanager.clarion.ws.entity.ImportError;
import br.com.kohen.eventmanager.clarion.ws.entity.ImportPurchaseError;
import br.com.kohen.eventmanager.commons.entity.Company;
import br.com.kohen.eventmanager.commons.entity.Purchase;

@SuppressWarnings({"rawtypes", "unchecked"})
@Component("logService")
public class LogServiceImpl implements LogService {

	
	@Autowired
	private ClarionPurchaseRepository purchaseRepository;
	
	@Autowired
	private ClarionCompanyRepository companyRepository;
	
	@Autowired
	private ClarionCompanyImportErrorRepository companyImportErrorReposiory;
	
	@Autowired
	private ClarionPurchaseImportErrorRepository purchaseImportErrorReposiory;
	
	@Override
	public void saveCompanyError(ImportError error) {
		ImportError errorLoaded = companyImportErrorReposiory.findOne(error.getId());
		
		if (errorLoaded != null) {
			errorLoaded.setCause(error.getCause());
			errorLoaded.setCauseEx(error.getCauseEx());
			companyImportErrorReposiory.save(errorLoaded);
		} else {
			companyImportErrorReposiory.save(error);
		}
	}

	@Override
	public void savePurchaseError(ImportPurchaseError error) {
		ImportPurchaseError errorLoaded = purchaseImportErrorReposiory.findOne(error.getId());
		
		if (errorLoaded != null) {
			errorLoaded.setCause(error.getCause());
			errorLoaded.setCauseEx(error.getCauseEx());
			purchaseImportErrorReposiory.save(errorLoaded);
		} else {
			purchaseImportErrorReposiory.save(error);
		}
	}

	@Override
	public void deleteError(Purchase purchase) {
		ImportPurchaseError errorLoaded =  purchaseImportErrorReposiory.findOne(purchase.getId());
		
		if (errorLoaded != null) {
			purchaseImportErrorReposiory.delete(errorLoaded);
		}
		
	}

	@Override
	public void deleteError(Company company) {
		ImportError errorLoaded = companyImportErrorReposiory.findOne(company.getId());
		
		if (errorLoaded != null) {
			companyImportErrorReposiory.delete(errorLoaded);
		}
		
	}

}
