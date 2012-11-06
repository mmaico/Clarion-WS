package br.com.kohen.eventmanager.clarion.dao;

import java.util.List;

import br.com.kohen.eventmanager.clarion.ws.entity.ImportError;
import br.com.kohen.eventmanager.commons.dao.CommonBaseDAO;
import br.com.kohen.eventmanager.commons.entity.Company;

@SuppressWarnings("rawtypes")

public interface ClarionCompanyDAO extends CommonBaseDAO  {

	
	public List<Company> getAllCompanyNotImported();
	
	public ImportError getCompanyErrorById(Long id);
		
	
	
}
