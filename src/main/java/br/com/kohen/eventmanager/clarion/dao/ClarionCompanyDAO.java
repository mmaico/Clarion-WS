package br.com.kohen.eventmanager.clarion.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.kohen.eventmanager.commons.dao.CommonBaseDAO;
import br.com.kohen.eventmanager.commons.dao.impl.CommonBaseDAOHibernate;
import br.com.kohen.eventmanager.commons.entity.Company;

@SuppressWarnings("rawtypes")

public interface ClarionCompanyDAO extends CommonBaseDAO  {

	
	public List<Company> getAllCompanyNotImported();
		
	
	
}
