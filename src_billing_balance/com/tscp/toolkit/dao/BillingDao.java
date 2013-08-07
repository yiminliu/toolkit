package com.tscp.toolkit.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tscp.toolkit.domain.billing.Invoice;

@Repository
@Transactional
public class BillingDao extends HibernateDaoSupport implements IBillingDao{
		
	public BillingDao(){}
	
	@Autowired
	@Qualifier("hibernateTemplateOracle")
	public void init(HibernateTemplate hibernateTemplate) {
	    setHibernateTemplate(hibernateTemplate);
	}

	@Transactional(readOnly=true)
	public List<Invoice> getInvoiceByInvoiceAccountNo(String accountNo){
	   List<Invoice> invoiceList = (List<Invoice>)getHibernateTemplate().find("from Invoice where account_no = ?", accountNo);
	   return invoiceList; 
	}
		
	@Transactional(readOnly=true)
	public float getKenanRealBalance(int accountNo){
		String queryString = "select ROUND(max(kenanBalance)+sum(proRatedAmount)+sum(webAmt)-sum(discountAmt), 3) " +
                     		 "from TcMobMins where accountNo = ? " +
                     		 "group by accountNo ";
		return doQuery(queryString, accountNo)*-1;
	}
		
	@Transactional(readOnly=true)
	public float getTotalPayments(int accountNo){
		String queryString = "select sum(transAmount)/100.00 from BmfMvno where bmfTransType in (1, 2, 3, 4, 7, 8, 16, 21, 80, -50) and accountNo = ? ";
		return doQuery(queryString, accountNo);
	}
	
	@Transactional(readOnly=true)
	public float getTotalAdjusts(int accountNo){
		return getCredit(accountNo) + getRefundAndAdjust(accountNo); 
		//String queryString = "select sum(transAmount)/100.00 from BmfMvno where bmfTransType in (-1, -40, 9, 11, 12, 13, 14, 15, 17, 19, 24, 25, 26, 27, 28, 30, 31, 32, 51, 53, 90) and accountNo = ? ";
		//return doQuery(queryString, accountNo); 
	}
	
	@Transactional(readOnly=true)
	public float getCreditCardPayments(int accountNo){
		String queryString = "select sum(transAmount)/100.00 from BmfMvno where bmfTransType in (7, 8) and accountNo = ? ";
		return doQuery(queryString, accountNo);
	}
	
	@Transactional(readOnly=true)
	public float getNonCreditCardPayments(int accountNo){		
		return getTotalPayments(accountNo) - getCreditCardPayments(accountNo);
	}
		
	@Transactional(readOnly=true)
	public float getCredit(int accountNo){
		//String queryString = "select sum(transAmount)/100.00 from BmfMvno where bmfTransType in (-1, -40, 9, 11, 12, 13, 14, 15, 17, 19, 24, 25, 26, 27, 28, 30, 31, 32, 51, 90) and accountNo = ? ";
		String queryString = "select sum(transAmount)/100.00 from BmfMvno where bmfTransType in (-1, 9) and accountNo = ? ";
		return doQuery(queryString, accountNo);
	}
	
	public float getRefundAndAdjust(int accountNo){
		String queryString = "select sum(transAmount)/100.00 from BmfMvno where bmfTransType in (-40, -51, 12, 13, 14, 15, 17, 19, 23, 24, 25, 26, 27, 28, 30, 31, 32, 51, 90) and accountNo = ? ";
		return getReverse(accountNo) + getAdjust(accountNo); 
	}
	
	@Transactional(readOnly=true)
	public float getReverse(int accountNo){
		//String queryString = "select sum(transAmount)/100.00 from BmfMvno where bmfTransType = 53 and accountNo = ? ";
		//String queryString = "select sum(transAmount)/100.00 from BmfMvno where bmfTransType in (-40, -51, 11, 12, 13, 14, 15, 17, 19, 23, 24, 25, 26, 27, 28, 30, 31, 32, 51, 90) and accountNo = ? ";
		String queryString = "select sum(transAmount)/100.00 from BmfMvno where bmfTransType in (-40, -51, 12, 13, 14, 15, 17, 19, 23, 24, 25, 26, 27, 28, 30, 31, 32, 51, 90) and accountNo = ? ";
		return doQuery(queryString, accountNo); 
	}	
		
	@Transactional(readOnly=true)
	public float getAdjust(int accountNo){
		String queryString = "select sum(i.invoiceDetail.amount/100) " +
        "from Invoice i " +
        "where i.invoiceDetail.typeCode = 3 " +
               "and i.invoiceDetail.subTypeCode = 4022 " +
               "and i.prepStatus = 1 " +
               //"and i.backoutStatus = 0 " +
               //"and i.prepErrorCode is null " +
                "and i.accountNo = ? "; 		
		return doQuery(queryString, accountNo); 
	}	
	
	@Transactional(readOnly=true)
	public float getTotalCharges(int accountNo){
		String queryString = "select sum(i.invoiceDetail.amount/100) " +
                             "from Invoice i " +
                             "where i.invoiceDetail.typeCode in(2, 3) and i. accountNo = ? ";
		return doQuery(queryString, accountNo);
	}
	
	@Transactional(readOnly=true)
	public float getMRCs(int accountNo){
		return getMRCFromInvoice(accountNo) + getMRCFromTcMobMins(accountNo);
	}
	
	@Transactional(readOnly=true)
	public float getMRCFromInvoice(int accountNo){
		String queryString = "select sum(i.invoiceDetail.amount/100) " +
				             "from Invoice i " +
				             "where i.invoiceDetail.typeCode = 2 " +
				             "and i.prepStatus = 1 " +
				             "and i.prepErrorCode IS NULL " +
				             "and i.accountNo = ? "; 				            
		return doQuery(queryString, accountNo); 
	}
		
	@Transactional(readOnly=true)
	public float getMRCFromTcMobMins(int accountNo){
		String queryString = "select sum(tmm.proRatedAmount) " +
				             "from TcMobMins tmm " +				            
				             "where tmm.accountNo = ? ";
		return doQuery(queryString, accountNo);
	}
	
	@Transactional(readOnly=true)
	public List<Object[]> getMRCFromInvoiceByMonth(int accountNo){
		String queryString = "select i.invoiceDetail.transDate, i.invoiceDetail.amount/100 " +
				             "from Invoice i " +
				             "where i.invoiceDetail.typeCode = 2 and " +
				             "i.prepStatus = 1 and " +
				             "i.prepErrorCode IS NULL and " +
				             "i.accountNo = " + accountNo +
				             " group by i.invoiceDetail.transDate, i.invoiceDetail.amount " +
				             "union all " +
				             "select chgDate, proRatedAmount from tcMobMins where accountNo = " + accountNo;
				             
		List<Object[]> list = (List<Object[]>)getHibernateTemplate().find(queryString);
				
		return list;  
	}
	
	@Transactional(readOnly=true)
	public float getMRCFromTcMobMinsByMonth(int accountNo){
		String queryString = "select sum(tmm.proRatedAmount) " +
				             "from TcMobMins tmm " +
				             "where tmm.accountNo = ? ";
		return doQuery(queryString, accountNo);
	}
	
	
	@Transactional(readOnly=true)
	public float getTotalUsageCharges(int accountNo) {
		return getNRCFromInvoice(accountNo) + getUsageFromTcMobMins(accountNo);
	}
	
	@Transactional(readOnly=true)
	public float getNRCFromInvoice(int accountNo){
		String queryString = "select sum(i.invoiceDetail.amount/100) " +
				             "from Invoice i " +
				             "where i.invoiceDetail.typeCode = 3 " +
				                    "and i.invoiceDetail.subTypeCode = 4024 " +
				                    "and i.prepStatus = 1 " +
				                    "and i.backoutStatus = 0 " +
    		                        "and i.prepErrorCode is null " +
 				                    "and i.accountNo = ? "; 		
		
		/*from Dave
		select nvl(sum(amount)/100,0) NRC
		from bill_invoice_detail bid, bill_invoice bi 
		where bid.index_bill_ref = bi.index_bill_ref
		and BI.ACCOUNT_NO = 709851
		and bid.type_code = 3
		and bid.subtype_code = 4024
		and bi.prep_status =1
		and bi.backout_status =0
		and bi.prep_error_code is null;
		*/
		return doQuery(queryString, accountNo); 
	}
	
	@Transactional(readOnly=true)
	public float getUsageFromTcMobMins(int accountNo){
		String queryString = "select sum(tmm.webAmt) " +
				             "from TcMobMins tmm " +
				             "where tmm.accountNo = ? ";
		return doQuery(queryString, accountNo);
	}
	
   //@Transactional(readOnly=true)	
   public float doQuery(String queryString, int queryParam) {
	   List<Double> amountList = (List<Double>)getHibernateTemplate().find(queryString, queryParam);
	   if(amountList != null && amountList.size() > 0){	
	      try { 
	  		  return new Float(amountList.get(0));
	      }	
	      catch(Exception e){ 
	         return 0;
	      }
	  	}   
	  	else
	  	   return 0; 
   }
	/* bmf_reans_type
	select b.bmf_trans_type, d.description_text
	from bmf_trans_descr b, descriptions d
	where b.description_code = d.description_code
	and d.language_code=1
	Result:
	1	Payment - Thank you
	2	Pre-Payment
	3	Correction Payment
	4	OCA Payment
	11	Payment Reversal
	12	Pre-Payment Reversal
	13	Correction Payment Reversal
	14	OCA Payment Reversal
	15	Dishonored Check Reversal
	21	Deposit Payment
	22	Deposit Interest
	23	Tax Liability
	24	Deposit Payment Reversal
	25	Deposit Interest Reversal
	26	Tax Liability Reversal
	27	Refund
	28	Refund Reversal
	30	Refund by Check
	31	Refund by Credit Card
	32	Refund by Electronic Funds Transfer
	40	Writeoff Payment
	80	System Payment
	16	Prepaid Tax Payment
	51	Backout Payment Reversal (Credit)
	52	Backout Payment (Debit)
	53	Currency Rounding Adjustment
	90	Payment Reversal
	-1	Invoice Credit
	-40	Writeoff Payment Reversal
	-50	Installation Payment
	-51	Installation Deposit Refund
	-52	Required Deposit
	33	Negative Balance Payment
	7	Credit Card Payment - Thank You
	17	Credit Card Payment Reversal
	8	Automatic Credit Card Payment - Thank You
	9	Coupon Credit
	19	Coupon Credit Reversal
    */
	
}
