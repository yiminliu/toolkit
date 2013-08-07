package com.tscp.toolkit.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tscp.toolkit.dao.IBalanceAuditDao;
import com.tscp.toolkit.domain.balance.BalanceAudit;

@Service
public class BalanceAuditService {

	@Autowired
	private IBalanceAuditDao balanceAuditDao;
	
	public void saveBalanceAudit(BalanceAudit balanceAudit){
		balanceAuditDao.save(balanceAudit);
	}
	
	public void batchSaveBalanceAudit(List<BalanceAudit> balanceAuditList){
		balanceAuditDao.batchSave(balanceAuditList);
	}
	
	public BalanceAudit getBalanceAudit(int accountNo){
		return balanceAuditDao.getBalanceAudit(accountNo);
	}
	
	public Set<BalanceAudit> getAllBalanceAuditRecords(){
		return balanceAuditDao.getAllBalanceAuditRecords();
	}
}
