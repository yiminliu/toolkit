package com.tscp.toolkit.client.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tscp.toolkit.dao.BillingDao;
import com.tscp.toolkit.dao.ChargeHistoryDao;
import com.tscp.toolkit.dao.IBillingDao;
import com.tscp.toolkit.dao.UsageDao;
import com.tscp.toolkit.domain.billing.ChargeHistory;
import com.tscp.toolkit.domain.billing.Invoice;


public class BillingDaoTestClient {

	private static  IBillingDao bDao;// = new chargeHistoryDaoHibernate();
    private static int accountNo = 699594; //751884;
    
	
	public static void main(String[] args){

		BillingDaoTestClient bt = new BillingDaoTestClient();
		try {
    		init();
    		bt.getMRCs(accountNo);
	    	bt.getMRCFromInvoice(accountNo);
	    	bt.getMRCByMonth(accountNo);
		    //bt.testGetChargeHistoryByAccountNo(501806);
		    //bt.testGetTotalMRC(testAccountNo);
		    //bt.getTotalAmount(accountNo);
		    //bt.getChargeAmountFromAmsProd(707242);
		    //bt.getChargeAmountFromAmsBilled(707242);
		    //bt.getTotalChargeAmount(707242);
		    //bt.getBeginingUsageDate(accountNo);
		     
    		//bt.getUsageAmount(accountNo);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}			
	
	
	public void getMRCFromInvoice(int accountNo){
		double amount = bDao.getMRCFromInvoice(accountNo);
		System.out.println("getMRCFromInvoice: total amount = $"+ amount);
	}
	
	public void getMRCs(int accountNo){
		double amount = bDao.getMRCs(accountNo);
		System.out.println("getMRCs: total amount = $"+ amount);
	}
	
	public void getMRCByMonth(int accountNo){
		Date month = null;		
		Double amount = 0D;
		double mrcAmount = 0D;
		List<Object[]> idList = bDao.getMRCFromInvoiceByMonth(accountNo);
		System.out.println("Kenan MRC by month");
		for(Object[] obj : idList){
			month = (Date)obj[0];
			amount = (Double)obj[1];	
			System.out.printf("Date: %s    Amount: %.2f %n", month, amount);
			mrcAmount += amount;
		}		
		System.out.println("Total = " + mrcAmount);		
	}
		
	private static void init(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("tscpmvne_toolkit-context.xml");
		bDao = (IBillingDao)ctx.getBean("billingDao");
	}

}
