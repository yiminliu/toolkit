package com.tscp.toolkit.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tscp.toolkit.dao.IBillingDao;
import com.tscp.toolkit.dao.IChargeHistoryDao;
import com.tscp.toolkit.dao.IUsageDao;
import com.tscp.toolkit.dao.UsageDao;
import com.tscp.toolkit.domain.billing.ChargeHistory;
import com.tscp.toolkit.domain.billing.InvoiceDetail;

@Service
public class ChargeAuditService {
			
	private static final float CUTOFF_VALUE = 0.95F;
	@Autowired
	private IBillingDao billingDao;	
	@Autowired 
	private IUsageDao usageDao;	
	@Autowired
	private IChargeHistoryDao chDao;	
					
	public boolean compareTotalCharges(int accountNo){
		float chAmount = chDao.getTotalCharges(accountNo);
		float kenanAmount = billingDao.getMRCs(accountNo) + billingDao.getTotalUsageCharges(accountNo);//usageDao.getTotalUsageCharges(accountNo);
		//float kenanAmount = billingDao.getTotalCharges(accountNo);
		return displayResult("TotalCharges ", kenanAmount, chAmount);	
	}
		
	
	public boolean compareUsageCharges(int accountNo){
		float chUsageChargeAmount = chDao.getUsageCharges(accountNo);
		float invoiceUsageChargeAmount = billingDao.getTotalUsageCharges(accountNo); //usageDao.getTotalUsageCharges(accountNo);
		float amsUsageChargeAmount = usageDao.getTotalUsageCharges(accountNo);
		return displayResult("UsageCharges ", invoiceUsageChargeAmount, amsUsageChargeAmount, chUsageChargeAmount);		
	}
	
	public boolean compareUsageChargesByMdn(int accountNo){
		List<Object[]> kenanUsageList = usageDao.getUsageByMdn(accountNo);
		List<Object[]> chUsageList = chDao.getUsageChargeByMdn(accountNo);
		float kenanSum = 0;
		System.out.println("********* Kenan usage charge by Mdn *********");
		for(Object[] usage : kenanUsageList){
		    String userName = (String)usage[0];
		    userName = userName.substring(0, userName.indexOf("@"));
		    Float dollarAmount = new Float((Double)usage[1]);	
		    kenanSum+= dollarAmount;
			System.out.printf("Mdn:  %s $%f%n", userName, dollarAmount);
		}
		System.out.printf("Total:  $%f%n",kenanSum);
		float dynamicSum = 0;
		System.out.println("********* Chargehistory usage charge by Mdn *********");
		for(Object[] ch : chUsageList){	
			String userName = (String)ch[0];
			Float dollarAmount = Math.abs(new Float((Double)ch[1]));
			dynamicSum+= dollarAmount;
			System.out.printf("Mdn:  %s $%f%n", userName, dollarAmount);			
		}
		System.out.printf("Total:  $%f%n", dynamicSum);
		
		//	System.out.printf("Mdn %s UsageCharge %f%n", ((ChargeHistory)ch).getExternalId(), ((ChargeHistory)ch).getAmount());
		//}
		return ((kenanUsageList.size() == chUsageList.size()) && (Math.abs(kenanSum - dynamicSum) > CUTOFF_VALUE));
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
		float kenanOldUsageChargeAmount = usageDao.getChargesFromAmsBilled(accountNo);
		System.out.println("kenanOldUsageChargeAmount = " + kenanOldUsageChargeAmount);
	}
	
	public void compareAmsAndChargehistoryUsageTimestamp(int accountNo){
		
	}
	
	public boolean compareServiceFeeCharges(int accountNo){
		float chAmount = Math.abs(chDao.getMRCs(accountNo));
		float kenanAmount = billingDao.getMRCs(accountNo);
		return displayResult("ServiceFeeCharges", kenanAmount, chAmount);	
	}
		
	public void auditChMRCByMonth(int accountNo){
		double mrcAmount = 0D;
		List<ChargeHistory> chList = chDao.getMRCHistory(accountNo);
		System.out.println("ChargeHistory MRC by month");
		for(ChargeHistory ch : chList){
			System.out.printf("Date: %s    Amount: %.2f %n", ch.getDateAndTime(), ch.getAmount());
			mrcAmount += ch.getAmount();
		}		
		System.out.println("Total = " + mrcAmount);
	}
	
	public void auditKenanMRCByMonth(int accountNo){
		Date month = null;		
		Double amount = 0D;
		double mrcAmount = 0D;
		List<Object[]> idList = billingDao.getMRCFromInvoiceByMonth(accountNo);
		System.out.println("Kenan MRC by month");
		for(Object[] obj : idList){
			month = (Date)obj[0];
			amount = (Double)obj[1];	
			System.out.printf("Date: %s    Amount: %.2f %n", month, amount);
			mrcAmount += amount;
		}		
		System.out.println("Total = " + mrcAmount);		
	}
	
	public List<ChargeHistory> getAllChargeHistory(){
		return chDao.getAllChargeHistory();
	}
	
	private static boolean displayResult(String name, float kenanAmount, float chAmount){
		System.out.printf("Compare %s:             kenanAmount = %f,            chAmount = %f,        difference = %f%n ", name, kenanAmount, chAmount, (kenanAmount - chAmount));
		if(Math.abs(kenanAmount - chAmount) > CUTOFF_VALUE)
		   return false;
		else
		   return true;
	}
	
	private static boolean displayResult(int accountNo, float kenanAmount, float chAmount){
		System.out.printf("AccountNo= %s:  Kenan Balance = %f, Dayamic Balance = %f, Difference = %f%n ", accountNo, kenanAmount, chAmount, (kenanAmount - chAmount));	
		if(Math.abs(kenanAmount - chAmount) > CUTOFF_VALUE)
		   return false;
		else
		   return true;
	}
	
	private static boolean displayResult(String name, float invoiceAmount, float amsAmount, float chAmount){
		System.out.printf("Compare %s:          invoicAmount = %f,       amsAmount = %f,  chAmount = %f,     difference between Kenan and CH = %f%n ", name, invoiceAmount, amsAmount, chAmount, (invoiceAmount - chAmount));
		if(Math.abs(invoiceAmount - chAmount) > CUTOFF_VALUE)
		   return false;
		else
		   return true;
	}
}	
	/*
	 * public List<Invoice> getInvoiceByAccountNo(String accountNo){
	 
		return billingDao.getInvoiceByInvoiceAccountNo(accountNo);
	}
	
		
	public float getTotalMRCCharges(int accountNo){
		return billingDao.getTotalMRC(accountNo);
	}
}    */
