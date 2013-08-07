package com.tscp.toolkit.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tscp.toolkit.domain.billing.ChargeHistory;


@Repository
public class ChargeHistoryDao extends HibernateDaoSupport implements IChargeHistoryDao{
	
	public ChargeHistoryDao(){}
	
	@Autowired
	@Qualifier("hibernateTemplateOracle")
	public void init(HibernateTemplate hibernateTemplate) {
	    setHibernateTemplate(hibernateTemplate);
	    hibernateTemplate = getHibernateTemplate();
	}

	@Override
	@Transactional(readOnly=true)
	public List<ChargeHistory> getAllChargeHistory() {
		return (List<ChargeHistory>) getHibernateTemplate().find("from ChargeHistory");
	}
	
	//public List<ChargeHistory> getAllActiveAccountChargeHistory() {
	//	String queryString = "from ChargeHistory ch, cust_acct_map cam, where "
	//	return (List<ChargeHistory>) getHibernateTemplate().find("from ChargeHistory where ");
	//}
	
	@Override
	@Transactional(readOnly=true)
	public List<ChargeHistory> getChargeHistory(int accountNo) {
		return (List<ChargeHistory>) getHibernateTemplate().find("from ChargeHistory where accountNo = ?", accountNo);
	}
			
	@Override
	public float getChBalance(int accountNo, String appName){
		String queryString = "";
		if ("TSCPMVNE".equalsIgnoreCase(appName))
			queryString = "select sum(amount +(case when discount is null then 0 else discount end))from ChargeHistory where accountNo = ?";
		else //we have this because the values of the "Data" in charge_hitory in TSCPMVNA schema are positive 
			queryString = "select sum((case when type='Data' then (amount * -1) else amount end) +(case when discount is null then 0 else discount end))" +
		 	              "from ChargeHistory " +
			              "where accountNo = ?";
		return doQuery(queryString, accountNo, false);
		
	}
	/*
	@Override
	public float getChBalance(int accountNo){
		//String queryString = "select sum(amount +(case when discount is null then 0 else discount end))from ChargeHistory where accountNo = ?";
		//String queryString = "select sum((case when type='Data' then (amount * -1) else amount end) +(case when discount is null then 0 else discount end))" +
		//		             "from ChargeHistory " +
		//		             "where accountNo = ?";
		//return doQuery(queryString, accountNo, false);
		return getChOtherBalance(accountNo) + getChDataBalance(accountNo);
	}
	
	@Override
	public float getChOtherBalance(int accountNo){
		//String queryString = "select sum(amount +(case when discount is null then 0 else discount end))from ChargeHistory where accountNo = ?";
		//String queryString = "select sum((case when type='Data' then (amount * -1) else amount end) +(case when discount is null then 0 else discount end))" +
		//		             "from ChargeHistory " +
		//		             "where accountNo = ?";
		String queryString = "select sum((amount) +(case when discount is null then 0 else discount end)) " +
				             "from ChargeHistory " +
				             "where type != 'Data' and " +
				             "accountNo = ? ";				             
		return doQuery(queryString, accountNo, false);
	}
	
	@Override
	public float getChDataBalance(int accountNo){
		//String queryString = "select sum(amount +(case when discount is null then 0 else discount end))from ChargeHistory where accountNo = ?";
		//String queryString = "select sum((case when type='Data' then (amount * -1) else amount end) +(case when discount is null then 0 else discount end))" +
		//		             "from ChargeHistory " +
		//		             "where accountNo = ?";
		String queryString = "select sum(case when amount > 0 then (amount * -1) else amount end) " +
				             "from ChargeHistory " +
				             "where type = 'Data' and " +
				             "accountNo = " + accountNo; 
		return doQuery(queryString, accountNo, false);
	}
	*/
	@Override
	public float getTotalPayments(int accountNo){
		String queryString ="select sum(c.amount)from ChargeHistory c where (c.type = 'Auto Top-Up' or type = 'Web Payment') and c.accountNo = ? ";
		return doQuery(queryString, accountNo, true);
	}
	
	@Override
	public float getCreditCardPayments(int accountNo){
		String queryString ="select sum(c.amount)from ChargeHistory c where c.type in ('Auto Top-Up', 'Web Payment') and c.accountNo = ? ";
		return doQuery(queryString, accountNo, true);
	}
	
	@Override
	public float getTotalCharges(int accountNo){
		//String queryString ="select sum(c.amount)from ChargeHistory c where c.type in ( 'Data', 'Access Fee' ) and c.accountNo = ? ";
		String queryString ="select sum(abs(c.amount))from ChargeHistory c where c.type in ( 'Data', 'Access Fee' ) and c.accountNo = ? ";
		return doQuery(queryString, accountNo, true);
	}
		
	@Override
	public float getUsageCharges(int accountNo){
		String queryString = "select sum(c.amount)from ChargeHistory c where c.type = 'Data' and c.accountNo = ?";
		return doQuery(queryString, accountNo, true);
	}
	
	@Override
	public List<Object[]> getUsageChargeByMdn(int accountNo){
		String queryString = "select c.externalId, sum(c.amount)" +
				             "from ChargeHistory c " +
				             "where c.type = 'Data' and c.accountNo = ? group by externalId";
		return doQuery(queryString, accountNo);
	}
		
	@Override
	public float getMRCs(int accountNo){
		String queryString = "select sum(amount) from ChargeHistory where type = 'Access Fee' and accountNo = ?";
		return doQuery(queryString, accountNo, true);	
	}
	
	@Override
	public float getTotalAdjusts(int accountNo){
		String queryString ="select sum(c.amount)from ChargeHistory c where c.type in ('Credit', 'Coupon', 'Reversal', 'Balance Adjust', 'Balance Forfeit' ) and c.accountNo = ? ";
		return doQuery(queryString, accountNo, true);
	}
	
	@Override
	public float getCreditAndCoupon(int accountNo){
		String queryString = "select sum(amount) from ChargeHistory where type in ('Credit', 'Coupon') and accountNo = ?";
		return doQuery(queryString, accountNo, true);
	}
	
	@Override
	public float getRefundAndAdjust(int accountNo){
		String queryString = "select sum(amount) from ChargeHistory where accountNo = ? and type in ('Reversal', 'Balance Adjust', 'Balance Forfeit')";
		return doQuery(queryString, accountNo, true);		
	}
		
	@Override
	public Date getBeginingUsageDate(int accountNo){
		String queryString = "select min(startTime)from ChargeHistory where type = 'Data' and accountNo = ?";
		List<Date> dateList = (List<Date>)getHibernateTemplate().find(queryString, accountNo);
	 	if(dateList != null && dateList.size() > 0) 
 		  return dateList.get(0);
	 	else 
	  	  return null;
	}
	
	@Override
	public List<ChargeHistory> getMRCHistory(int accountNo){
		//String queryString = "select type, date_and_time, amount from ChargeHistory where accountNo = ? and type = 'Access Fee'";
		String queryString = "from ChargeHistory where type = 'Access Fee' and accountNo = ?";
		return (List<ChargeHistory>) getHibernateTemplate().find(queryString, accountNo);	
	}
	
	@Override
	@Transactional(readOnly=true)
	public float doQuery(String queryString, boolean absluteValue) {
	   List<Double> amountList = (List<Double>)getHibernateTemplate().find(queryString);
 	   if(amountList != null && amountList.size() > 0) {
	   	  try { 
 		       if(absluteValue){   
 		    	 if(amountList.get(0) != null)
 		    	    return new Float(Math.abs(amountList.get(0)));
 		    	 else
 		    		return 0; 
 		       }
 		       else
                  return new Float(amountList.get(0));
 		  }
 		  catch(Exception e){
 			  //e.printStackTrace();
 			  return 0;
 		  }
 	   }
 	   else
  	      return 0;
    }
	
	
	@Override
	@Transactional(readOnly=true)
	public float doQuery(String queryString, int queryParam, boolean absluteValue) {
	   List<Double> amountList = (List<Double>)getHibernateTemplate().find(queryString, queryParam);
 	   if(amountList != null && amountList.size() > 0) {
	   	  try { 
 		       if(absluteValue){   
 		    	 if(amountList.get(0) != null)
 		    	    return new Float(Math.abs(amountList.get(0)));
 		    	 else
 		    		return 0; 
 		       }
 		       else
                  return new Float(amountList.get(0));
 		  }
 		  catch(Exception e){
 			  //e.printStackTrace();
 			  return 0;
 		  }
 	   }
 	   else
  	      return 0;
    }
		
	@Override
	@Transactional(readOnly=true)
	public List<Object[]> doQuery(String queryString, int queryParam) {
	   return (List<Object[]>)getHibernateTemplate().find(queryString, queryParam);
	}	 	 
}
