package com.tscp.toolkit.dao;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.tscp.toolkit.domain.balance.BalanceAudit;

@Repository
public interface IBalanceAuditDao {

	public void save (BalanceAudit balanceAudit);
	
	public void batchSave(List<BalanceAudit> balanceAuditList);
	
	public BalanceAudit getBalanceAudit(Integer accountNo);
	
	public Set<BalanceAudit> getAllBalanceAuditRecords();
		
}
