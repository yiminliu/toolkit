package com.tscp.toolkit.jUnit.daoJunit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import oracle.net.aso.e;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tscp.toolkit.dao.BalanceAuditDao;
import com.tscp.toolkit.dao.IBalanceAuditDao;
import com.tscp.toolkit.domain.balance.BalanceAudit;

public class BalanceAuditDaoTest {

	private static  IBalanceAuditDao dao;
	
	private BalanceAudit balanceAudit;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("tscpmvne_toolkit-context.xml");
		dao = (IBalanceAuditDao)ctx.getBean("balanceAuditDao");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBalanceAuditDao() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveBalanceAudit() {
		int accountNo = 1234567;
		
		balanceAudit = new BalanceAudit();
		balanceAudit.setCustId(1234);
		balanceAudit.setAccountNo(accountNo);
		balanceAudit.setDynamicBalance(23.5F);
		balanceAudit.setKenanRealBalance(22.3F);
		
		dao.save(balanceAudit);
		
		BalanceAudit balanceAudit1 = dao.getBalanceAudit(accountNo);
		
		assertEquals(balanceAudit, balanceAudit1);
		
	}

	@Test
	public void testBatchSaveBalanceAudit() {
        int accountNo = 1234567;
		
		balanceAudit = new BalanceAudit();
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
		
		dao.batchSave(list);
		
	}

	@Test
	public void testGetBalanceAudit() {
		fail("Not yet implemented");
	}

}
