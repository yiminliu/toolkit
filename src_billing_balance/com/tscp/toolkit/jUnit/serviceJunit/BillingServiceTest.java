package com.tscp.toolkit.jUnit.serviceJunit;



import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tscp.toolkit.domain.billing.ChargeHistory;
import com.tscp.toolkit.domain.billing.Invoice;



public class BillingServiceTest {
		
/*	private BillingService billingService;// = new BillingServiceHibernate();
    private String testAccountNo = "501806";
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		init();
	}

	//public static void main(String[] args){
	//	BillingServiceTest bt = new BillingServiceTest();
	//	bt.init();
	//	bt.getInvoiceByAccountNo();
	//}
	
	//@Test
	public void testGetInvoiceByAccountNo(){
		System.out.println("getInvoiceByAccountNo");
	    List<Invoice> invoiceList = billingService.getInvoiceByAccountNo(testAccountNo);
	    System.out.println("Invoices: ");
	    for(Invoice invoice : invoiceList) {
    	    System.out.println(invoice.toString());
	    }    
	}
	*/
	//@Test
	/*public void testGetChargeHistory(){
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
	
	//@Test
	public void testGetChargeHistoryByAccountNo(){
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
	public void testGetTotalMRCCharges(){
		long startTime = System.currentTimeMillis();
		System.out.println("Start testGetChargeHistory()");
		float amount = billingService.getTotalMRC(testAccountNo);
		System.out.println("All MRC = $"+ amount);
	}
   */
	
	//@After
	//public void tearDown() throws Exception {
	//}
	
	//private void init(){
	//	ApplicationContext ctx = new ClassPathXmlApplicationContext("toolkit-context.xml");
	//	billingService = (BillingService)ctx.getBean("billingService");
	//}


}
