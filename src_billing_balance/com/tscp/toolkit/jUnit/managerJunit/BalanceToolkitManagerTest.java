package com.tscp.toolkit.jUnit.managerJunit;


import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tscp.toolkit.domain.balance.BalanceAudit;
import com.tscp.toolkit.manager.BalanceToolkitManager;
import com.tscp.toolkit.manager.LocalBalanceAuditManager;

public class BalanceToolkitManagerTest {

	private static final String APPNAME = "TSCPMVNE";
	
	private BalanceToolkitManager balanceAuditManager;
	
	private int accountNo = 725079; //748545; //774945; //775078; //775364; // //734983; //699594; //734837; ///698864; //692247; //737294;  //711110; ////735041; 714620; //692247;//735041; //709192;//
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx = null;;
		if (APPNAME.equalsIgnoreCase("TSCPMVNE"))
		    ctx = new ClassPathXmlApplicationContext("tscpmvne_toolkit-context.xml");
		else if (APPNAME.equalsIgnoreCase("TSCPMVNA"))
			ctx = new ClassPathXmlApplicationContext("tscpmvna_toolkit-context.xml");
		
		balanceAuditManager = (BalanceToolkitManager)ctx.getBean("balanceToolkitManager");
		
	}
	
	//@Test
	public void testAuditBalanceDiscrepancy() {
		int count = balanceAuditManager.auditBalanceDiscrepancy(APPNAME);
		Assert.assertEquals(0, count);
	}		
	
	//@Test
	public void testAuditAndSaveBalanceDiscrepancy() {
		balanceAuditManager.auditAndSaveBalanceDiscrepancy(APPNAME);
		//Assert.assertEquals(0, count);
	}
	
	//@Test
	public void testAuditLowKenanBalanceAccount() {
		int count = balanceAuditManager.auditLowKenanBalanceAccount();
		Assert.assertEquals(0, count);		
	}
	
	@Test
	public void testAuditBalances(){
		boolean result = balanceAuditManager.auditBalances(accountNo, APPNAME);
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testAuditTotalPayments() {
		boolean result = balanceAuditManager.auditTotalPayments(accountNo);
		Assert.assertEquals(true, result);
	}

	@Test
	public void testAuditTotalCharges() {
		boolean result = balanceAuditManager.auditTotalCharges(accountNo);
		Assert.assertEquals(true, result);
	}

	@Test
	public void testAuditTotalAdjusts() {
		boolean result = balanceAuditManager.auditTotalAdjusts(accountNo);
		Assert.assertEquals(true, result);
	}

	@Test
	public void testAuditUsageCharges() {
		if(!balanceAuditManager.auditTotalCharges(accountNo)){
		   boolean result = balanceAuditManager.auditUsageCharges(accountNo);
		   Assert.assertEquals(true, result);
		}
	}
	
	@Test
	public void testAuditServiceFeeCharges() {
		if(!balanceAuditManager.auditTotalCharges(accountNo)){
		   boolean result = balanceAuditManager.auditServiceFeeCharges(accountNo);
		   Assert.assertEquals(true, result);
		}   
	}

	@Test
	public void testAuditCreditAndCoupon() {
		if(!balanceAuditManager.auditTotalAdjusts(accountNo)){
		   boolean result = balanceAuditManager.auditCreditAndCoupon(accountNo);
		   Assert.assertEquals(true, result);
		}   
	}

	@Test
	public void testAudiitRefundAndAdjust() {
		if(!balanceAuditManager.auditTotalCharges(accountNo)){
		   boolean result = balanceAuditManager.auditRefundAndAdjust(accountNo);
		   Assert.assertEquals(true, result);
		}   
	}

	@Test
	public void testAuditCreditCardPayments() {
		if(!balanceAuditManager.auditTotalPayments(accountNo)) {
			boolean result = balanceAuditManager.auditCreditCardPayments(accountNo);
		    Assert.assertEquals(true, result);
	    }	
	}

	@Test
	public void testAuditNonCreditCardPayments() {
		if(!balanceAuditManager.auditTotalPayments(accountNo)) {
			boolean result = balanceAuditManager.auditNonCreditCardPayments(accountNo);
		    Assert.assertEquals(true, result);
		}    
	}

	@Test
	public void testAuditUsageChargesByMdn() {
		if(!balanceAuditManager.auditTotalPayments(accountNo)) {
		   boolean result = balanceAuditManager.auditUsageChargesByMdn(accountNo);
		   Assert.assertEquals(true, result);
		}   
	}

	//@Test
	public void testGetKenanNewUsageCharges() {
		fail("Not yet implemented");
	}

	//@Test
	public void testGetKenanOldUsageCharges() {
		fail("Not yet implemented");
	}

	//@Test
	public void testCompareAmsAndChargehistoryUsageTimestamp() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSaveBalanceAudit(){
		
        int accountNo = 1234567;
		
        BalanceAudit balanceAudit = new BalanceAudit();
		balanceAudit.setCustId(1234);
		balanceAudit.setAccountNo(accountNo);
		balanceAudit.setDynamicBalance(23.5F);
		balanceAudit.setKenanRealBalance(22.3F);
				
		balanceAuditManager.saveBalanceAudit(balanceAudit);
	}
	
	
	@Test
	public void testBatchSaveBalanceAudit(){
		
        int accountNo = 1234567;
		
        BalanceAudit balanceAudit = new BalanceAudit();
		balanceAudit.setCustId(1234);
		balanceAudit.setAccountNo(accountNo);
		balanceAudit.setDynamicBalance(23.5F);
		balanceAudit.setKenanRealBalance(22.3F);
				
		BalanceAudit balanceAudit1 = new BalanceAudit();
		balanceAudit1.setCustId(5678);
		balanceAudit1.setAccountNo(68975);
		balanceAudit1.setDynamicBalance(123.5F);
		balanceAudit1.setKenanRealBalance(122.3F);
		
		ArrayList<BalanceAudit> list = new ArrayList<BalanceAudit>();
		list.add(balanceAudit);
		list.add(balanceAudit1);
		
		balanceAuditManager.batchSaveBalanceAudit(list);
	}
	
	@Test
	public void testAuditAmsAndChargehistoryBeginingUsageTime() {
		boolean result = balanceAuditManager.auditAmsAndChargehistoryBeginingUsageTime(accountNo);
		Assert.assertEquals(true, result);
	}

	//@Test
	//public void testAuditChServiceFeeByMonth() {
	//	boolean result = balanceAuditManager.showChServiceFeeByMonth(accountNo);
	//	Assert.assertEquals(true, result);
	//}
	

	//@Test
	public void testGetAllChargeHistory() {
		fail("Not yet implemented");
	}
	
	@After
	public void tearDown() throws Exception {
		System.out.println("****************** Test Done *****************");
	}

}
