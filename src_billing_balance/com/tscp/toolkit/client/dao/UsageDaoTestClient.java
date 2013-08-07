package com.tscp.toolkit.client.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tscp.toolkit.dao.ChargeHistoryDao;
import com.tscp.toolkit.dao.UsageDao;
import com.tscp.toolkit.domain.billing.ChargeHistory;
import com.tscp.toolkit.domain.billing.Invoice;


public class UsageDaoTestClient {

	private static  UsageDao usageDao;// = new chargeHistoryDaoHibernate();
    private static int accountNo = 751884;
    
	
	public static void main(String[] args){

		UsageDaoTestClient bt = new UsageDaoTestClient();
		try {
    		init();
	    	//bt.getChargeHistory();
		    //bt.testGetChargeHistoryByAccountNo(501806);
		    //bt.testGetTotalMRC(testAccountNo);
		    //bt.getTotalAmount(accountNo);
		    //bt.getChargeAmountFromAmsProd(707242);
		    //bt.getChargeAmountFromAmsBilled(707242);
		    //bt.getTotalChargeAmount(707242);
		    bt.getBeginingUsageDate(accountNo);
		     
    		//bt.getUsageAmount(accountNo);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}			
	
	public void getTotalChargeAmount(int accountNo){
		   double amount = usageDao.getTotalUsageCharges(accountNo);
		   System.out.println("total amount = $"+ amount);
	}
	
	public void getChargeAmountFromAmsProd(int accountNo){
		   double amount = usageDao.getChargesFromAmsProd(accountNo);
		   System.out.println("total amount = $"+ amount);
	}
	
	public void getChargeAmountFromAmsBilled(int accountNo){
		   double amount = usageDao.getChargesFromAmsBilled(accountNo);
		   System.out.println("total amount = $"+ amount);
	}
	
	public void getBeginingUsageDate(int accountNo){
		   Date date = usageDao.getBeginingUsageDate(accountNo);
		   System.out.println("Begining date = "+ date);
	}
	private static void init(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("toolkit-context.xml");
		usageDao = (UsageDao)ctx.getBean("usageDao");
	}

}
