package com.tscp.toolkit.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tscp.toolkit.domain.balance.BalanceAudit;

@Repository
public class BalanceAuditDao extends HibernateDaoSupport implements IBalanceAuditDao{

	@Autowired
	@Qualifier("hibernateTemplateOracle")
	//@PostConstruct
	public void init(HibernateTemplate hibernateTemplate){
		setHibernateTemplate(hibernateTemplate);
	}
	
	public BalanceAuditDao(){}
	
	@Override
	public void save(BalanceAudit balanceAudit) {
		balanceAudit.setTimeStamp(new Date());
		getHibernateTemplate().saveOrUpdate(balanceAudit);
	}
	
	@Override
	@Transactional
	public void batchSave(List<BalanceAudit> balanceAuditList){
		HibernateTemplate ht = getHibernateTemplate();
		for(BalanceAudit ba : balanceAuditList){
			ba.setTimeStamp(new Date());
			ht.saveOrUpdate(ba);
		}
	}
	//public void batchSave(List<BalanceAudit> balanceAuditList){
	//	StatelessSession session  = getHibernateTemplate().getSessionFactory().openStatelessSession();
	//	Transaction tx = session.beginTransaction();
	//	for(BalanceAudit ba : balanceAuditList){
	//		session.insert(ba);	
	//	}
	//	tx.commit();
	//	session.close();
	//}

	@Override
	public BalanceAudit getBalanceAudit(Integer accountNo) {
		return (BalanceAudit)getHibernateTemplate().get(BalanceAudit.class, accountNo);
    }
	
	@Override
	public Set<BalanceAudit> getAllBalanceAuditRecords() {
		List<BalanceAudit> list = (List<BalanceAudit>)getHibernateTemplate().find("from BalanceAudit");
		return new HashSet(list);
	}
}
