package br.com.kohen.eventmanager.clarion.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.kohen.eventmanager.clarion.dao.ClarionPurchaseDAO;
import br.com.kohen.eventmanager.clarion.ws.entity.ImportPurchaseError;
import br.com.kohen.eventmanager.commons.dao.impl.CommonBaseDAOHibernate;
import br.com.kohen.eventmanager.commons.entity.Purchase;
import br.com.kohen.eventmanager.commons.enums.PurchaseStatus;

@SuppressWarnings({"rawtypes", "unchecked"})
@Repository("clarionPurchaseDAO")
public class ClarionPurchaseDAOImpl extends CommonBaseDAOHibernate implements ClarionPurchaseDAO  {

	
	
	@Override
	public List<Purchase> getAllPurchaseNotImported() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Purchase.class);
		criteria.add(Restrictions.isNull("code"));
		criteria.add(Restrictions.eq("status", PurchaseStatus.ACTIVE));
		
		return criteria.list();
	}

	@Override
	public ImportPurchaseError getPurchaseErrorById(Long id) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImportPurchaseError.class);
		criteria.add(Restrictions.eq("id", id));
		
		return (ImportPurchaseError) criteria.uniqueResult();
	}
	
	
}
