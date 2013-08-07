package com.tscp.toolkit.web.controller;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tscp.toolkit.domain.balance.BalanceAudit;
import com.tscp.toolkit.manager.BalanceToolkitManager;

@Controller
@RequestMapping("/{appName}/balance")
//@RequestMapping("/truconnect/balance")
public class BalanceToolkitController {
	@Autowired
	private BalanceToolkitManager balanceAuditManager;
	
	@RequestMapping(value = "/audit", method = RequestMethod.GET)
	public String showBalanceAuditForm(@PathVariable("appName") String appName){
		return appName + "/balance/audit";
	}
	
	@RequestMapping (value = "/audit", method = RequestMethod.POST)
	public ModelAndView processBalanceAudit(@RequestParam("accountNo") String accountNo, @PathVariable("appName") String appName) {
		ModelAndView modelAndView = new ModelAndView(appName + "/balance/auditResult");
		balanceAuditManager.setAppName(appName);
		balanceAuditManager.showServiceFeeByMonth(Integer.parseInt(accountNo));
		Set<BalanceAudit> balanceAuditSet = balanceAuditManager.getAllBalanceAuditRecords();
		modelAndView.addObject("accountNo", accountNo);
		modelAndView.addObject("balanceAuditList", new ArrayList(balanceAuditSet));
		return modelAndView;
	}
}
