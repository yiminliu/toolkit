package com.tscp.toolkit.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tscp.toolkit.domain.billing.ChargeHistory;


@Repository
public interface IChargeHistoryDao {
	
	@Transactional(readOnly=true)
	public List<ChargeHistory> getAllChargeHistory();
	
	
	//public List<ChargeHistory> getAllActiveAccountChargeHistory() {
	//	String queryString = "from ChargeHistory ch, cust_acct_map cam, where "
	//	return (List<ChargeHistory>) getHibernateTemplate().find("from ChargeHistory where ");
	//}
	
	@Transactional(readOnly=true)
	public List<ChargeHistory> getChargeHistory(int accountNo);
	
				
	public float getChBalance(int accountNo, String appName);
	
	
	public float getTotalPayments(int accountNo);
	
	public float getCreditCardPayments(int accountNo);
	
	public float getTotalCharges(int accountNo);
	
		
	public float getUsageCharges(int accountNo);
		
	public List<Object[]> getUsageChargeByMdn(int accountNo);
			
	public float getMRCs(int accountNo);
	@Transactional(readOnly=true)	
	public float getTotalAdjusts(int accountNo);
		
	@Transactional(readOnly=true)
	public float getCreditAndCoupon(int accountNo);
		
	@Transactional(readOnly=true)
	public float getRefundAndAdjust(int accountNo);
		
	@Transactional(readOnly=true)
	public Date getBeginingUsageDate(int accountNo);
		
	@Transactional(readOnly=true)
	public List<ChargeHistory> getMRCHistory(int accountNo);
	
	@Transactional(readOnly=true)
	public float doQuery(String queryString, boolean absluteValue);
	
	@Transactional(readOnly=true)
	public float doQuery(String queryString, int queryParam, boolean absluteValue);
			
	@Transactional(readOnly=true)
	public List<Object[]> doQuery(String queryString, int queryParam);
 
}
