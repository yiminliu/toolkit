package com.tscp.toolkit.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tscp.toolkit.dao.IChargeHistoryDao;
import com.tscp.toolkit.dao.IDeviceDao;
import com.tscp.toolkit.dao.IUsageDao;
import com.tscp.toolkit.domain.balance.BalanceAudit;
import com.tscp.toolkit.domain.billing.ChargeHistory;
import com.tscp.toolkit.domain.device.DeviceInfo;

@Service
public class OverallAuditService {
			
	private static final float CUTOFF_VALUE = 0.95F;

	@Autowired 
	private IUsageDao usageDao;	
	@Autowired
	private IChargeHistoryDao chDao;	
	@Autowired 
	private IDeviceDao deviceDao;
	@Autowired
	private PaymentAuditService paymentAuditService;
	@Autowired
	ChargeAuditService chargeAuditService;
	@Autowired
	private AdjustAuditService adjustAuditService;
	
	
	@Transactional(readOnly=true)
	public void auditBalanceDiscrepancy(String appName){
		System.out.println("Start Audit");
		long startTime = System.currentTimeMillis();
		
		Set<DeviceInfo> deviceSet = getActiveAndSusppendedDevices();
				
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		System.out.printf("The number of ative devices returned %d%n ", deviceSet.size());
		
		for(DeviceInfo di : deviceSet){
			compareDynamicAndKenanCurrentBalances(di.getAccountNo(), appName);			
		}
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		
		System.out.printf("Total Time = %d Sec", totalTime/1000);
	}
	
	public boolean compareDynamicAndKenanCurrentBalances(int accountNo, String appName){
		float chBalance = paymentAuditService.getChBalance(accountNo, appName);
		float kenanCurrentBalance = paymentAuditService.getKenanRealBalance(accountNo);
		float difference = chBalance - kenanCurrentBalance;
		displayResult(accountNo, kenanCurrentBalance, chBalance);
		if(Math.abs(difference) > CUTOFF_VALUE) {
			displayResult(accountNo, kenanCurrentBalance, chBalance);	
	        return false;
		}
		else
		   return true;	
	}
	
	public BalanceAudit auditAndSaveBalanceDiscrepancy(DeviceInfo deviceInfo, String appName){
		BalanceAudit balanceAudit = null;
		float chBalance = paymentAuditService.getChBalance(deviceInfo.getAccountNo(), appName);
		float kenanRealBalance = paymentAuditService.getKenanRealBalance(deviceInfo.getAccountNo());
		float difference = chBalance - kenanRealBalance;
		//displayResult(accountNo, kenanCurrentBalance, chBalance);
		if(Math.abs(difference) > CUTOFF_VALUE) {
			balanceAudit = new BalanceAudit();
			balanceAudit.setCustId(deviceInfo.getCustId());
			balanceAudit.setAccountNo(deviceInfo.getAccountNo());
			balanceAudit.setDynamicBalance(chBalance);
			balanceAudit.setKenanRealBalance(kenanRealBalance);
		}
		return balanceAudit;
	}
	
	public boolean getLowKenanBalanceAccounts(int accountNo, String accountStatus){
		float kenanRealBalance = paymentAuditService.getKenanRealBalance(accountNo);
		if (Math.abs(kenanRealBalance) < 2) {
			System.out.printf("AccountNo= %s:  Kenan Real Balance = %.2f    Account Status = %s %n", accountNo, kenanRealBalance, accountStatus);		
	        return false;		
	    }
		else
		   return true;	
	}
	
	/*This is used to compare the Payments from ChargeHistory and Kenan Bmf_mvno table
	 * 
	 */
	public boolean compareTotalPayments(int accountNo){
		return paymentAuditService.compareTotalPayments(accountNo);
	}
			
	public void compareTotalCharges(int accountNo){
		chargeAuditService.compareTotalCharges(accountNo);	
	}
	
	public void compareTotalAdjusts(int accountNo){
		adjustAuditService.compareTotalAdjusts(accountNo);	
	}
	
	public void compareUsageCharges(int accountNo){
		chargeAuditService.compareUsageCharges(accountNo);		
	}
	
	public void compareUsageChargesByMdn(int accountNo){
		chargeAuditService.compareUsageChargesByMdn(accountNo);
	}
	
	/*
	 public void compareUsageChargesByMdn(int accountNo){
		List<Object[]> kenanUsageList = usageDao.getUsageByMdn(accountNo);
		List<Object[]> chUsageList = chDao.getUsageChargeByMdn(accountNo);
		
		List<String> kenanMdnList = new ArrayList<String>();
		List<Float> kenanUsageChargeAmountList = new ArrayList<Float>();
		List<String> chMdnList = new ArrayList<String>();
		List<Float> chUageChargeAmountList = new ArrayList<Float>();
		
		//for(Object[] usage : kenanUsageList){
		for(int i = 0; i < kenanUsageList.size(); i++){
			Object[] usage = kenanUsageList.get(i);
		    String userName = (String)usage[0];
		    userName = userName.substring(0, userName.indexOf("@"));
		    Float dollatAmount = new Float((Double)usage[1]);
		    kenanMdnList.add(i, userName);
		    kenanUsageChargeAmountList.add(i, dollatAmount);
			//System.out.printf("Mdn %s UsageCharge %f%n", userName, dollatAmount);
		}
		//for(ChargeHistory ch : chUsageList){
		//for(Object[] ch : chUsageList){	
		for(int i = 0; i < chUsageList.size(); i++){
			Object[] ch = chUsageList.get(i);
			String userName = (String)ch[0];
			Float dollatAmount = Math.abs(new Float((Double)ch[1]));
			chMdnList.add(i, userName);
			chUageChargeAmountList.add(i, dollatAmount);
			//System.out.printf("Mdn %s UsageCharge %f%n", userName, dollatAmount);	
		}
		//	System.out.printf("Mdn %s UsageCharge %f%n", ((ChargeHistory)ch).getExternalId(), ((ChargeHistory)ch).getAmount());
		//}
		if(kenanMdnList.size() >= chMdnList.size()){
		   for(int j = 0; j < kenanMdnList.size(); j++){
			   String kenanMdn = kenanMdnList.get(j);
			   
			   System.out.printf("Mdn %s UsageCharge %f%n", userName, dollatAmount);	
			   
		   }
		}
		
	}
	
	public void getKenanNewUsageCharges(int accountNo){
		float kenanNewUsageChargeAmount = usageDao.getChargesFromAmsProd(accountNo);
		System.out.println("kenanNewUsageChargeAmount = " + kenanNewUsageChargeAmount);
	}
	
	public void getKenanOldUsageCharges(int accountNo){
		float kenanOldUsageChargeAmount = usageDao.getChargesFromAmsBilled(accountNo);
		System.out.println("kenanOldUsageChargeAmount = " + kenanOldUsageChargeAmount);
	}
	*/
	
	public void getKenanNewUsageCharges(int accountNo){
		float kenanNewUsageChargeAmount = usageDao.getChargesFromAmsProd(accountNo);
		System.out.println("kenanNewUsageChargeAmount = " + kenanNewUsageChargeAmount);
	}
	
	public void getKenanOldUsageCharges(int accountNo){
		chargeAuditService.getKenanOldUsageCharges(accountNo);
	}
	
	public void compareAmsAndChargehistoryUsageTimestamp(int accountNo){
		
	}
	
	public void compareServiceFeeCharges(int accountNo){
		chargeAuditService.compareServiceFeeCharges(accountNo);	
	}
	
	public void compareCreditAndCouponAndRefund(int accountNo){
		adjustAuditService.compareCreditAndCoupon(accountNo);	
	}
	
	public void compareRefundAndAdjust(int accountNo){
		adjustAuditService.compareRefundAndAdjust(accountNo);	
		System.out.println();
	}
	
	public void compareCreditCardPayments(int accountNo){
		paymentAuditService.compareCreditCardPayments(accountNo);
	}
	
	public void compareNonCreditCardPayments(int accountNo){
		paymentAuditService.compareNonCreditCardPayments(accountNo);
	}
	
	public void compareStartAmsAndChargehistoryUsageTime(int accountNo){
		Date chTime = chDao.getBeginingUsageDate(accountNo);
		Date kenanTime = usageDao.getBeginingUsageDate(accountNo);
		int difference = chTime.compareTo(kenanTime);
		System.out.println("kenan Start Time = " + kenanTime);
		System.out.println("ch Start Time = " + chTime);
		if(difference < 0)
		   System.out.println("Chargehistory start time is earlier than AMS start time, indicating that some usage data is missing in the AMS table for the given account number, maybe due to account number was not linked with DMN(ie. component was not provisioned for the account)");	
	}
	
	public void auditMRCByMonth(int accountNo){
		List<ChargeHistory> chList = chDao.getMRCHistory(accountNo);
		for(ChargeHistory ch : chList){
			System.out.printf("Date: %s    Amount: %.2f %n", ch.getDateAndTime(), ch.getAmount());
		}
	}
	
	public Set<DeviceInfo> getActiveAndSusppendedDevices(){
		return deviceDao.getActiveAndSusppendedDevices();
	}
	
	public Set<DeviceInfo> getActiveDevices(){
		return deviceDao.getActiveDevices();
	}
	
	public List<ChargeHistory> getAllChargeHistory(){
		return chDao.getAllChargeHistory();
	}
	
	private static void displayResult(String name, float kenanAmount, float chAmount){
		System.out.printf("Compare %s:             kenanAmount = %f,            chAmount = %f,        difference = %f%n ", name, kenanAmount, chAmount, (kenanAmount - chAmount));
	}
	
	private static void displayResult(int accountNo, float kenanAmount, float chAmount){
		System.out.printf("AccountNo= %s:  Kenan Balance = %f, Dayamic Balance = %f, Difference = %f%n ", accountNo, kenanAmount, chAmount, (kenanAmount - chAmount));	
	}
}	
	