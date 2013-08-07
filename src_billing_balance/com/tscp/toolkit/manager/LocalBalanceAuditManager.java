package com.tscp.toolkit.manager;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tscp.toolkit.dao.IBillingDao;
import com.tscp.toolkit.dao.IChargeHistoryDao;
import com.tscp.toolkit.dao.IDeviceDao;
import com.tscp.toolkit.dao.IUsageDao;
import com.tscp.toolkit.domain.billing.ChargeHistory;
import com.tscp.toolkit.domain.device.DeviceInfo;
import com.tscp.toolkit.service.AdjustAuditService;
import com.tscp.toolkit.service.ChargeAuditService;
import com.tscp.toolkit.service.OverallAuditService;
import com.tscp.toolkit.service.PaymentAuditService;

@Service
public class LocalBalanceAuditManager {
	
	@Autowired
	OverallAuditService overallAuditService;
	private static final float CUTOFF_VALUE = 0.99F;
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
	
	public int auditBalanceDiscrepancy(String appName){
		System.out.println("Start Audit Balance Discrepany Between Kenan and Dynamic Balances");
		long startTime = System.currentTimeMillis();
		int count = 0;
		Set<DeviceInfo> deviceSet = overallAuditService.getActiveAndSusppendedDevices();
								
		for(DeviceInfo di : deviceSet){
			if(!overallAuditService.compareDynamicAndKenanCurrentBalances(di.getAccountNo(), appName)) {
			   count++;
			}   
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.printf("%d Accounts Found with Balance Discrepany %n", count);
		System.out.printf("Total Time = %d Sec. %n", totalTime/1000);
		return count;
	}
	
	public int auditLowKenanBalanceAccount(){
		System.out.println("Start Audit Low Balance");
	    int count = 0;
		long startTime = System.currentTimeMillis();
		Set<DeviceInfo> deviceSet = overallAuditService.getActiveDevices();
		System.out.println("Number of active devices = " + deviceSet.size());
		for(DeviceInfo di : deviceSet){
			if(!overallAuditService.getLowKenanBalanceAccounts(di.getAccountNo(), toStatus(di.getStatusId())))	
			   count++; 
		}
		System.out.printf("%d Accounts Found with Low Kenan Real Balance (< $2) %n", count);
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.printf("Total Time = %d Sec. %n", totalTime/1000);
		return count;
	}
	
	
	public boolean auditBalances(int accountNo, String appName){
		boolean result = false;
		System.out.println("************** Audit Balances between ChargeHistotory and Kenan current balance *****************");	
		  
		if(overallAuditService.compareDynamicAndKenanCurrentBalances(accountNo, appName)) {
		   System.out.println("******************* Kenan Current Balance and Dynamic Balance Are Equal *****************");
		   result = true;
		}
		else {
		    System.out.println("****************** Audit Charges *****************");	
			auditCharges(accountNo);
			
			System.out.println("****************** Audit payments ****************");
			auditPayments(accountNo);
				  
			System.out.println("****************** Audit adjusts *****************");
			auditAdjusts(accountNo);
		}   
		return result;    
	}
		
	public boolean compareDynamicAndKenanCurrentBalances(int accountNo, String appName){
		float chBalance = paymentAuditService.getChBalance(accountNo, appName);
		float kenanCurrentBalance = paymentAuditService.getKenanRealBalance(accountNo);
		float difference = chBalance - kenanCurrentBalance;
		//displayResult("DynamicAndKenanCurrentBalances", kenanCurrentBalance, chBalance);
		if(Math.abs(difference) > CUTOFF_VALUE) {
			displayResult(accountNo, kenanCurrentBalance, chBalance);	
	        return false;
		}
		else
		   return true;	
	}
		
	public boolean auditCharges(int accountNo){
	    boolean result = false;
		if(auditTotalCharges(accountNo)){
		   result = true;
		}
		else {   
		   System.out.println("Charges Check Failed");	
		   if(!auditUsageCharges(accountNo)){
		      System.out.println("Usage Charges Check Failed !!!");
		      if(!auditUsageChargesByMdn(accountNo))
			     System.out.println("Usage Charges by MDN Check Failed !!!");
		   }   
		   if(!auditServiceFeeCharges(accountNo)) {
		       System.out.println("MRC Charges Check Failed !!!");
		       showServiceFeeByMonth( accountNo);
		   }
		}  return result; 
	} 
			
	public boolean auditPayments(int accountNo){
		boolean result = false;
		if(auditTotalPayments(accountNo)) {
		   result = true;
		}
		else {
		   System.out.println("Payments Check Failed !!!");
		   if(!auditCreditCardPayments(accountNo))
			  System.out.println("CreditCard Payments Check Failed !!!");
		   if(!auditNonCreditCardPayments(accountNo))
			  System.out.println("Non-CreditCard Payments Check Failed !!!");
		}
		return result;
	}
	
	public boolean auditAdjusts(int accountNo){
		boolean result = false;
		if(auditTotalAdjusts(accountNo)) {
		   result = true;
		}
		else {
		   System.out.println("Total Adjusts Check Failed !!!");	
		   if(!auditCreditAndCoupon(accountNo))
		  	  System.out.println("Credit and Coupons Check Failed !!!"); 
		   if(!auditRefundAndAdjust(accountNo))
		      System.out.println("Other Adjusts Check Failed !!!");
		}
		return result;
	}
	
    public boolean auditTotalCharges(int accountNo){
		return chargeAuditService.compareTotalCharges(accountNo);	
	}
	
    /*This is used to compare the Payments from ChargeHistory and Kenan Bmf_mvno table
	 * 
	 */
	public boolean auditTotalPayments(int accountNo){
		return paymentAuditService.compareTotalPayments(accountNo);
	}
	
	public boolean auditTotalAdjusts(int accountNo){
		return adjustAuditService.compareTotalAdjusts(accountNo);	
	}
	
	public boolean auditUsageCharges(int accountNo){
		boolean result = chargeAuditService.compareUsageCharges(accountNo);
		if(!result) {
			auditUsageChargesByMdn(accountNo);
			auditAmsAndChargehistoryBeginingUsageTime(accountNo);
		}	
		return result;	
	}
	
	public boolean auditUsageChargesByMdn(int accountNo){
		return chargeAuditService.compareUsageChargesByMdn(accountNo);
	}
		
	public void getKenanNewUsageCharges(int accountNo){
		float kenanNewUsageChargeAmount = usageDao.getChargesFromAmsProd(accountNo);
		System.out.println("kenanNewUsageChargeAmount = " + kenanNewUsageChargeAmount);
	}
	
	public void getKenanOldUsageCharges(int accountNo){
		chargeAuditService.getKenanOldUsageCharges(accountNo);
	}
	
	public void compareAmsAndChargehistoryUsageTimestamp(int accountNo){
		
	}
	
	public boolean auditServiceFeeCharges(int accountNo){
		boolean result = chargeAuditService.compareServiceFeeCharges(accountNo);
		if(!result){
			showServiceFeeByMonth(accountNo);	
		}	
		return 	result;
	}
	
	public boolean auditCreditAndCoupon(int accountNo){
		return adjustAuditService.compareCreditAndCoupon(accountNo);	
	}
	
	public boolean auditRefundAndAdjust(int accountNo){
		return adjustAuditService.compareRefundAndAdjust(accountNo);	
	}
	
	public boolean auditCreditCardPayments(int accountNo){
		return paymentAuditService.compareCreditCardPayments(accountNo);
	}
	
	public boolean auditNonCreditCardPayments(int accountNo){
		return paymentAuditService.compareNonCreditCardPayments(accountNo);
	}
	
	public boolean auditAmsAndChargehistoryBeginingUsageTime(int accountNo){
		long cutOff = 10 * 60 * 1000; //10 mins
		Date chTime = chDao.getBeginingUsageDate(accountNo);
		Date kenanTime = usageDao.getBeginingUsageDate(accountNo);
		Calendar chCalendar = Calendar.getInstance();
		Calendar kenanCalendar = Calendar.getInstance();
		chCalendar.setTime(chTime);
		kenanCalendar.setTime(kenanTime);
		long difference = Math.abs(chCalendar.getTimeInMillis() - kenanCalendar.getTimeInMillis());
		
		System.out.println("******** Compare Kenan and Charge History Usage Start Time *********"); 
		System.out.println("        kenan  Start Time = " + kenanTime);
		System.out.println("charge history Start Time = " + chTime);
				
		if(difference > cutOff){
		   System.out.println("Chargehistory start time and AMS start time are different, indicating that some usage data is missing in the AMS table for the given account number, maybe due to account number was not linked with DMN(ie. component was not provisioned for the account)");	
	       return false;	   
		}
		else
		   return true;	
	}
	
	public void showServiceFeeByMonth(int accountNo){
		/*List<ChargeHistory> chList = chDao.getMRCHistory(accountNo);
		for(ChargeHistory ch : chList){
			System.out.printf("Date: %s    Amount: %.2f %n", ch.getDateAndTime(), ch.getAmount());
		}
		List<ChargeHistory> chList = chDao.getMRCHistory(accountNo);
		for(ChargeHistory ch : chList){
			System.out.printf("Date: %s    Amount: %.2f %n", ch.getDateAndTime(), ch.getAmount());
		}*/
		chargeAuditService.auditChMRCByMonth(accountNo);
		chargeAuditService.auditKenanMRCByMonth(accountNo);
	}
	
	public Set<DeviceInfo> getActiveAndSusppendedDevices(){
		return deviceDao.getActiveAndSusppendedDevices();
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
	
	private static String toStatus(int statusId){
		String status = "Unkown";
		switch(statusId) {
			case 1:
				status = "New";
				break;
			case 2:
				status = "Active";
				break;
			case 3:
				status = "Deactivated";
				break;
			case 4:
				status = "Removed";
				break;
			case 5:
				status = "Suspended";
				break;
			case 6:
				status = "Blocked";
				break;
		}
		return status;
	}
}
