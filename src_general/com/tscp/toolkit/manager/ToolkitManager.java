package com.tscp.toolkit.manager;

public class ToolkitManager {

	private BalanceToolkitManager balanceAuditManager;
	
	private ToolkitManager(){}
	
	private ToolkitManager(String appName){
		balanceAuditManager = new BalanceToolkitManager(appName);
	}
	
	
}
