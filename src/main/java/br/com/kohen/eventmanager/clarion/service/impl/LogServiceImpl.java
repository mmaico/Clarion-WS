package br.com.kohen.eventmanager.clarion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.kohen.eventmanager.clarion.service.LogService;
import br.com.kohen.eventmanager.clarion.ws.entity.ImportError;
import br.com.kohen.eventmanager.clarion.ws.entity.ImportPurchaseError;
import br.com.kohen.eventmanager.commons.dao.CommonBaseDAO;
import br.com.kohen.eventmanager.commons.entity.Company;
import br.com.kohen.eventmanager.commons.entity.Purchase;

@SuppressWarnings({"rawtypes", "unchecked"})
@Component("logService")
public class LogServiceImpl implements LogService {

	
	@Autowired
	@Qualifier("commonBaseDAO")
	private CommonBaseDAO baseDAO;
	
	@Override
	public void saveCompanyError(ImportError error) {
		ImportError errorLoaded = (ImportError) baseDAO.findById(ImportError.class, error.getId());
		
		if (errorLoaded != null) {
			error.setCause(error.getCause());
			error.setCauseEx(error.getCauseEx());
			baseDAO.saveOrUpdate(error);
		} else {
			baseDAO.saveOrUpdate(error);
		}
		
	}

	@Override
	public void savePurchaseError(ImportPurchaseError error) {
		ImportPurchaseError errorLoaded = (ImportPurchaseError) baseDAO.findById(ImportPurchaseError.class, error.getId());
		
		if (errorLoaded != null) {
			error.setCause(error.getCause());
			error.setCauseEx(error.getCauseEx());
			baseDAO.saveOrUpdate(error);
		} else {
			baseDAO.saveOrUpdate(error);
		}
	}

	@Override
	public void deleteError(Purchase purchase) {
		ImportPurchaseError errorLoaded = (ImportPurchaseError) baseDAO.findById(ImportPurchaseError.class, purchase.getId());
		
		if (errorLoaded != null) {
			baseDAO.delete(errorLoaded);
		}
		
	}

	@Override
	public void deleteError(Company company) {
		ImportError errorLoaded = (ImportError) baseDAO.findById(ImportError.class, company.getId());
		
		if (errorLoaded != null) {
			baseDAO.delete(errorLoaded);
		}
		
	}

}
