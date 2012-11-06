package br.com.kohen.eventmanager.clarion.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.kohen.eventmanager.clarion.dao.ClarionCompanyDAO;
import br.com.kohen.eventmanager.clarion.ws.entity.ImportError;
import br.com.kohen.eventmanager.commons.dao.impl.CommonBaseDAOHibernate;
import br.com.kohen.eventmanager.commons.entity.Company;

@SuppressWarnings("rawtypes")
@Repository("clarionCompanyDAO")
public class ClarionCompanyDAOImpl extends CommonBaseDAOHibernate implements ClarionCompanyDAO  {

	
	@SuppressWarnings("unchecked")
	public List<Company> getAllCompanyNotImported() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Company.class);
		criteria.add(Restrictions.isNull("code"));
		
		
		return criteria.list();
		
	}
	
	@Override
	public ImportError getCompanyErrorById(Long id) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImportError.class);
		criteria.add(Restrictions.eq("id", id));
		
		return (ImportError) criteria.uniqueResult();
	}
	
	
}
