package com.tscp.toolkit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tscp.toolkit.dao.ChargeHistoryDao;
import com.tscp.toolkit.dao.BillingDao;
import com.tscp.toolkit.dao.IBillingDao;
import com.tscp.toolkit.dao.IChargeHistoryDao;


@Service
public class PaymentAuditService {
			
	private static final float CUTOFF_VALUE = 0.05F;
	
	@Autowired
	private IBillingDao billingDao;	
	@Autowired
	private IChargeHistoryDao chDao;	

	/*This is used to compare the Payments from ChargeHistory and Kenan bmf_mvno table
	 * 
	 */
	public boolean compareTotalPayments(int accountNo){
		float chAmount = chDao.getTotalPayments(accountNo);
		float kenanAmount = billingDao.getTotalPayments(accountNo);
		return displayResult("TotalPayments", kenanAmount, chAmount);	
	}
		
	public boolean compareCreditCardPayments(int accountNo){
		float chAmount = chDao.getCreditCardPayments(accountNo);
		float kenanAmount = billingDao.getCreditCardPayments(accountNo);
		return displayResult("CreditCardPayments", kenanAmount, chAmount);
	}
	
	public boolean compareNonCreditCardPayments(int accountNo){
		float chAmount = 0;//chDao.getCreditCardPayments(accountNo);
		float kenanAmount = billingDao.getNonCreditCardPayments(accountNo);
		return displayResult("NonCreditCardPayments", kenanAmount, chAmount);
	}

	public float getChBalance(int accountNo, String appName){
		return chDao.getChBalance(accountNo, appName);
	}
	
	public float getKenanRealBalance(int accountNo){
		return  billingDao.getKenanRealBalance(accountNo);
	}
	
	private static boolean displayResult(String name, float kenanAmount, float chAmount){
		System.out.printf("Compare %s:             kenanAmount = %f,            chAmount = %f,        difference = %f%n ", name, kenanAmount, chAmount, (kenanAmount - chAmount));
		if(Math.abs(kenanAmount - chAmount) > CUTOFF_VALUE)
		   return false;
		else
		   return true;		
	}
	
	private static void displayResult(int accountNo, float kenanAmount, float chAmount){
		System.out.printf("AccountNo= %s:  Kenan Balance = %f, Dayamic Balance = %f, Difference = %f%n ", accountNo, kenanAmount, chAmount, (kenanAmount - chAmount));	
	   //System.out.println("chBalance = " + chBalance);
		//System.out.println("diff  = " + difference);
	}
}	
	
