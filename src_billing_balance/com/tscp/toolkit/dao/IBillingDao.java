package com.tscp.toolkit.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tscp.toolkit.domain.billing.Invoice;
import com.tscp.toolkit.domain.billing.InvoiceDetail;

@Repository
@Transactional
public interface IBillingDao {
		
		
	@Transactional(readOnly=true)
	public List<Invoice> getInvoiceByInvoiceAccountNo(String accountNo);
	
	@Transactional(readOnly=true)
	public float getKenanRealBalance(int accountNo);
		
		
	@Transactional(readOnly=true)
	public float getTotalPayments(int accountNo);
	
	
	@Transactional(readOnly=true)
	public float getTotalAdjusts(int accountNo);
		
	@Transactional(readOnly=true)
	public float getCreditCardPayments(int accountNo);
		
	@Transactional(readOnly=true)
	public float getNonCreditCardPayments(int accountNo);
			
	@Transactional(readOnly=true)
	public float getCredit(int accountNo);
	
	
	@Transactional(readOnly=true)
	public float getRefundAndAdjust(int accountNo);
			
	@Transactional(readOnly=true)
	public float getTotalCharges(int accountNo);
		
	@Transactional(readOnly=true)
	public float getMRCs(int accountNo);
	
	@Transactional(readOnly=true)
	public float getMRCFromInvoice(int accountNo);
			
	@Transactional(readOnly=true)
	public float getMRCFromTcMobMins(int accountNo);
		
	@Transactional(readOnly=true)
	public List<Object[]> getMRCFromInvoiceByMonth(int accountNo);
		
	
	@Transactional(readOnly=true)
	public float getTotalUsageCharges(int accountNo);
		
	
	@Transactional(readOnly=true)
	//This is used to get usage charges
	public float getNRCFromInvoice(int accountNo);
		   
}
