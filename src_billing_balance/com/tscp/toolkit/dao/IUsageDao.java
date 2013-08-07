package com.tscp.toolkit.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IUsageDao {	
	
	public float getTotalUsageCharges(Integer accountNo);	
	
	public float getChargesFromAmsProd(Integer accountNo);
		
	public float getChargesFromAmsBilled(Integer accountNo);
			
	public List<Object[]> getUsageByMdn(Integer accountNo);
			
	@Transactional(readOnly=true)
	public List<Object[]> getAmsBilledUsageByMdn(Integer accountNo);
		
	@Transactional(readOnly=true)
	public List<Object[]> getAmsProdUsageByMdn(Integer accountNo);
			
	@Transactional(readOnly=true)
	public Date getBeginingUsageDate(int accountNo);
		
}
