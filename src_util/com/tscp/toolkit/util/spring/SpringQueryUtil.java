package com.tscp.toolkit.util.spring;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class SpringQueryUtil {
	
	public static List<Object[]> doQuery(HibernateTemplate hibernateTemplate, String queryString, int queryParam){
		return (List<Object[]>)hibernateTemplate.find(queryString, queryParam);
	}

	public static List<Object[]> doQuery(HibernateTemplate hibernateTemplate, String queryString, int queryParam, int queryParam2){
		return (List<Object[]>)hibernateTemplate.find(queryString, queryParam);
	}
	public static float doQuery(HibernateTemplate hibernateTemplate, String queryString, int queryParam, boolean absluteValue) {
		   List<Double> amountList = (List<Double>)hibernateTemplate.find(queryString, queryParam);
		   if(amountList != null && amountList.size() > 0) {
			   try {
				   if(absluteValue)   
	 			      return new Float(Math.abs(amountList.get(0)));
	 		       else
	                  return new Float(amountList.get(0));
	 	       }
		       catch(Exception e){
	 		      return 0;
		       }   
	 	   }
	 	   else
	  	      return 0;
	    }
}
