package com.tscp.toolkit.dao;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tscp.toolkit.util.spring.SpringQueryUtil;

@Repository
public class UsageDao extends HibernateDaoSupport implements IUsageDao{
	
	public UsageDao(){}
	
	@Autowired
	@Qualifier("hibernateTemplateOracle")
	//@PostConstruct
	public void init(HibernateTemplate hibernateTemplate) {
	    setHibernateTemplate(hibernateTemplate);
	    hibernateTemplate = getHibernateTemplate();
	}
		
	@Override
	public float getTotalUsageCharges(Integer accountNo){		
		float apAmount = getChargesFromAmsProd(accountNo);
		float abAmount = getChargesFromAmsBilled(accountNo);		
		return apAmount + abAmount;
	}
	
	@Override
	public float getChargesFromAmsProd(Integer accountNo){
		String queryString = "select sum(ap.dollarCharge) from AmsProd ap where ap.accountNo = ?";
		return SpringQueryUtil.doQuery(getHibernateTemplate(), queryString, accountNo, true); 
	}
	
	@Override
	public float getChargesFromAmsBilled(Integer accountNo){
		String queryString = "select sum(ab.dollarCharge) from AmsBilled ab where ab.accountNo = ?";
		return SpringQueryUtil.doQuery(getHibernateTemplate(), queryString, accountNo, true);
	}
	
	@Override
	public List<Object[]> getUsageByMdn(Integer accountNo){
		List<Object[]> list1 =getAmsBilledUsageByMdn(accountNo);
		List<Object[]> list2 = getAmsProdUsageByMdn(accountNo);
		list1.addAll(list2);
		return list1;
		//String queryString = "select userName, sum(dollarCharge) " +
		//		             "from AmsBilled " +
		//		             "where accountNo = ? group by userName " +
		 //                    "UNION ALL " +
		//		             "select userName, sum(dollarCharge) " +
		//		             "from AmsProd " +
		//		             "where accountNo = ? group by userName ";       
		//return SpringQueryUtil.doQuery(getHibernateTemplate(), queryString, accountNo, accountNo);
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Object[]> getAmsBilledUsageByMdn(Integer accountNo){
		String queryString = "select userName, sum(dollarCharge) " +
				             "from AmsBilled " +
				             "where accountNo = ? group by userName ";
		return SpringQueryUtil.doQuery(getHibernateTemplate(), queryString, accountNo);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Object[]> getAmsProdUsageByMdn(Integer accountNo){
		String queryString = "select userName, sum(dollarCharge) " +
				             "from AmsProd " +
				             "where accountNo = ? group by userName ";       
		return SpringQueryUtil.doQuery(getHibernateTemplate(), queryString, accountNo);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Date getBeginingUsageDate(int accountNo){
		String queryStringAb = "select min(tscpActualTimestamp)from AmsBilled where accountNo = ?";
		String queryStringAp = "select min(tscpActualTimestamp)from AmsProd where accountNo = ?";
		List<Date> dateList = (List<Date>)getHibernateTemplate().find(queryStringAb, accountNo);
	 	   if(dateList != null && dateList.size() > 0) {
 			  return dateList.get(0);
	 	   }
	 	   else {
	 			 dateList = (List<Date>)getHibernateTemplate().find(queryStringAp, accountNo);
	 		 	 if(dateList != null && dateList.size() > 0) 
	 	 		    return dateList.get(0);
	 	 		 else
	  	            return null;
	 	   }	 	 
	}
	
	
}
