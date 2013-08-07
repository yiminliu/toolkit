package com.tscp.toolkit.client.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tscp.toolkit.dao.ChargeHistoryDao;
import com.tscp.toolkit.domain.billing.ChargeHistory;
import com.tscp.toolkit.domain.billing.Invoice;


public class ChargeHistoryDaoTestClient {

	private static  ChargeHistoryDao chargeHistoryDao;// = new chargeHistoryDaoHibernate();
    private static int accountNo = 707242;
    
	
	public static void main(String[] args){
		ChargeHistoryDaoTestClient bt = new ChargeHistoryDaoTestClient();
		try {
    		init();
	    	//bt..getChargeHistory();
		    //bt.testGetChargeHistoryByAccountNo(501806);
		    //bt.testGetTotalMRC(testAccountNo);
		    //bt.getTotalAmount(accountNo);
		   //bt.getTotalPayments(707242);
		    //bt.getTotalCreditAndCoupon(accountNo);
    		//bt.getUsageAmount(accountNo);
		    bt.getBeginingUsageDate(accountNo);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}		
		
	public void getChargeHistory(){
		long startTime = System.currentTimeMillis();
		System.out.println("Start testGetChargeHistory()");
		List<ChargeHistory> chList = chargeHistoryDao.getAllChargeHistory();
		System.out.println("Retrieved total number of charge history "+ chList.size());
		for(ChargeHistory ch : chList){
	    	System.out.println(ch.toString());
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime-startTime;
		System.out.println("Total time: " + totalTime);
			
	}	
		
	public void testGetChargeHistoryByAccountNo(int accountNo){
		long startTime = System.currentTimeMillis();
		System.out.println("Start testGetChargeHistory()");
		List<ChargeHistory> chList = chargeHistoryDao.getChargeHistory(accountNo);
		System.out.println("Retrieved total number of charge history "+ chList.size());
		for(ChargeHistory ch : chList){
			System.out.println(ch.toString());
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime-startTime;
		System.out.println("Total time: " + totalTime);
	}
	
	
	public void getBalance(int accountNo, String appName){
		   double amount = chargeHistoryDao.getChBalance(accountNo, appName);
		   System.out.println("total amount = $"+ amount);
	}
	
	public void getUsageAmount(int accountNo){
		   double amount = chargeHistoryDao.getUsageCharges(accountNo);
		   System.out.println("total amount = $"+ amount);
	}
	
	public void testGetTotalMRC(int accountNo){
	   double amount = chargeHistoryDao.getMRCs(accountNo);
	   System.out.println("All MRC = $"+ amount);
	}	
	
	public void getTotalPayments(int accountNo){
		   double amount = chargeHistoryDao.getTotalPayments(accountNo);
		   System.out.println("total amount = $"+ amount);
	}
	
	public void getTotalCreditAndCoupon(int accountNo){
	   double amount = chargeHistoryDao.getCreditAndCoupon(accountNo);
	   System.out.println("total amount = $"+ amount);	
	}   
	
	public void getBeginingUsageDate(int accountNo){
		Date date = chargeHistoryDao.getBeginingUsageDate(accountNo);
		System.out.println("Begining date = "+ date);
	}
	private static void init(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("tscpmvne_toolkit-context.xml");
		chargeHistoryDao = (ChargeHistoryDao)ctx.getBean("chargeHistoryDao");
	}

}
