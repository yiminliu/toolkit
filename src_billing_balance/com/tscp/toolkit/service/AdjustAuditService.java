package com.tscp.toolkit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tscp.toolkit.dao.IBillingDao;
import com.tscp.toolkit.dao.IChargeHistoryDao;

@Service
public class AdjustAuditService {
		
	private static final float CUTOFF_VALUE = 0.05F;
	
	@Autowired
	private IChargeHistoryDao chDao;	
	@Autowired
	private IBillingDao billingDao;	
	
	public boolean compareTotalAdjusts(int accountNo){
		float chAmount = chDao.getTotalAdjusts(accountNo);
		float kenanAmount = billingDao.getTotalAdjusts(accountNo);
		return displayResult("TotalAdjusts  ", kenanAmount, chAmount);	
	}
		
	public boolean compareCreditAndCoupon(int accountNo){
		float chAmount = chDao.getCreditAndCoupon(accountNo);
		float kenanAmount = billingDao.getCredit(accountNo);
		return displayResult("CreditAndCoupon", kenanAmount, chAmount);	
	}
	
	public boolean compareRefundAndAdjust(int accountNo){
		float chAmount = chDao.getRefundAndAdjust(accountNo);
		float kenanAmount = billingDao.getRefundAndAdjust(accountNo);
		return displayResult("Refund and Adjusts", kenanAmount, chAmount);	
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