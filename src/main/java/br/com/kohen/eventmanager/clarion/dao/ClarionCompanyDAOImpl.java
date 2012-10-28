package br.com.kohen.eventmanager.clarion.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	
	
}
