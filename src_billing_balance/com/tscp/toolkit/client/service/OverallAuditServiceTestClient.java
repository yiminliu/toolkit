package com.tscp.toolkit.client.service;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tscp.toolkit.domain.billing.ChargeHistory;
import com.tscp.toolkit.domain.billing.Invoice;
import com.tscp.toolkit.jUnit.serviceJunit.BillingServiceTest;
import com.tscp.toolkit.service.OverallAuditService;

public class OverallAuditServiceTestClient {

	private static String appName = "TSCPMVNE";
	private static  OverallAuditService overalAuditService;// = new BillingServiceHibernate();
    private static int accountNo = 725079; //752427; //777441;//  //748545; //774945;//776119; //697994; //698864; //737294; // 711110; //735041; //724021; //711110; //; //710613;  //692247;//707242;// 724021;//709192; //735041;//709851; 707242;//770933;
    
	
	public static void main(String[] args){
		OverallAuditServiceTestClient tc = new OverallAuditServiceTestClient();
		tc.init();
		//bt.testGetChargeHistoryByAccountNo(501806);
		
		//tc.auditBalanceDiscrepancy();
		
		//Overall summary
		System.out.println("********** Overall Summary *********");
		tc.compareDynamicAndKenanCurrentBalances(accountNo, appName);
		
		//overall breakdown
		System.out.println("********** Overall Breakdown *********");
		tc.compareCharges(accountNo);
		tc.comparePayments(accountNo);
		tc.compareCreditAndRefund(accountNo);
		tc.compareOtherAdjusts(accountNo);
				
		//charge breakdown
		System.out.println("********** Charge Sumary *********");
		tc.compareUsageCharges(accountNo);
			
		//individual usage charges
		tc.compareUsageChargesByMdn(accountNo);
		tc.getKenanNewUsageCharges(accountNo);
		tc.getKenanOldUsageCharges(accountNo);
		
		tc.compareMRCs(accountNo);	
		
		//Payment Breakdown
		System.out.println("********** Payment Breakdown *********");
		tc.compareCreditCardPayments(accountNo);
		tc.compareNonCreditCardPayments(accountNo);
			
		
	//	tc.compareStartAmsAndChargehistoryUsageTime(accountNo);
		
		
		
		
		//tc.auditMRCByMonth(accountNo);	
	}
	
	/*public void auditBalanceDiscrepancy(String appName){
		overalAuditService.auditBalanceDiscrepancy(appName);
	}
	*/
	public void compareDynamicAndKenanCurrentBalances(int accountNo, String appName){
		overalAuditService.compareDynamicAndKenanCurrentBalances(accountNo, appName);
	}
	
	public void comparePayments(int accountNo){
		overalAuditService.compareTotalPayments(accountNo);
	}
	
	public void compareCharges(int accountNo){
		overalAuditService.compareTotalCharges(accountNo);
	}
	
	public void compareCreditCardPayments(int accountNo){
		overalAuditService.compareCreditCardPayments(accountNo);
	}
	
	public void compareNonCreditCardPayments(int accountNo){
		overalAuditService.compareNonCreditCardPayments(accountNo);
	}
	
	public void compareUsageCharges(int accountNo){
		overalAuditService.compareUsageCharges(accountNo);
	}
	
	public void compareUsageChargesByMdn(int accountNo){
		overalAuditService.compareUsageChargesByMdn(accountNo);
	}
	
	public void getKenanNewUsageCharges(int accountNo){
		overalAuditService.getKenanNewUsageCharges(accountNo);
	}
	
	public void getKenanOldUsageCharges(int accountNo){
		overalAuditService.getKenanOldUsageCharges(accountNo);
	}
	
	public void compareMRCs(int accountNo){
		overalAuditService.compareServiceFeeCharges(accountNo);
	}
	
	public void compareCreditAndRefund(int accountNo){
		overalAuditService.compareCreditAndCouponAndRefund(accountNo);
	}
	
	public void compareOtherAdjusts(int accountNo){
		overalAuditService.compareRefundAndAdjust(accountNo);
	}
		
	public void compareStartAmsAndChargehistoryUsageTime(int accountNo){
		overalAuditService.compareStartAmsAndChargehistoryUsageTime(accountNo);
	}
	
	public void auditMRCByMonth(int accountNo){
		overalAuditService.auditMRCByMonth(accountNo);
	}
	
	public void getChargeHistory(){
		long startTime = System.currentTimeMillis();
		System.out.println("Start testGetChargeHistory()");
		List<ChargeHistory> chList = overalAuditService.getAllChargeHistory();
		System.out.println("Retrieved total number of charge history "+ chList.size());
		for(ChargeHistory ch : chList){
			System.out.println(ch.toString());
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime-startTime;
		System.out.println("Total time: " + totalTime);
	}		
		
	/*
	public void getInvoiceByAccountNo(){
		System.out.println("getInvoiceByAccountNo");
		 List<Invoice> invoiceList = billingService.getInvoiceByAccountNo(testAccountNo);
		    System.out.println("Invoices: ");
		    for(Invoice invoice : invoiceList) {
	    	    System.out.println(invoice.toString());
		    }    
	}
	*/	
		/*public void getChargeHistory(){
			long startTime = System.currentTimeMillis();
			System.out.println("Start testGetChargeHistory()");
			List<ChargeHistory> chList = billingService.getChargeHistory();
			System.out.println("Retrieved total number of charge history "+ chList.size());
			for(ChargeHistory ch : chList){
				System.out.println(ch.toString());
			}
			long endTime = System.currentTimeMillis();
			long totalTime = endTime-startTime;
			System.out.println("Total time: " + totalTime);
			
		}	
		*/
	/*	public void testGetChargeHistoryByAccountNo(int accountNo){
			long startTime = System.currentTimeMillis();
			System.out.println("Start testGetChargeHistory()");
			List<ChargeHistory> chList = billingService.getChargeHistoryByAccountNo(accountNo);
			System.out.println("Retrieved total number of charge history "+ chList.size());
			for(ChargeHistory ch : chList){
				System.out.println(ch.toString());
			}
			long endTime = System.currentTimeMillis();
			long totalTime = endTime-startTime;
			System.out.println("Total time: " + totalTime);
			
		}
		@Test
		public void testGetTotalMRCCharges(int accountNo){
			long startTime = System.currentTimeMillis();
			System.out.println("Start testGetChargeHistory()");
			float amount = billingService.getTotalMRCCharges(accountNo);
			System.out.println("All MRC = $"+ amount);
		}	
		*/
	private void init(){
		ApplicationContext ctx = null;
		if (appName.equalsIgnoreCase("TSCPMVNE"))
		    ctx = new ClassPathXmlApplicationContext("tscpmvne_toolkit-context.xml");
		else if (appName.equalsIgnoreCase("TSCPMVNA"))
			ctx = new ClassPathXmlApplicationContext("tscpmvna_toolkit-context.xml");
					
		overalAuditService = (OverallAuditService)ctx.getBean("overallAuditService");
	}

}
