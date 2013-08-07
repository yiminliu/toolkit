package com.tscp.toolkit.domain.billing;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="tc_mob_mins")
 @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TcMobMins implements Serializable{

	@Id
	@Column(name="ACCOUNT_NO")
	private int accountNo;
	
	@Column(name="MDN")
	private String mdn;
	
	@Column(name="BILL_PERIOD")
	private String billPeriod;
	
	@Column(name="PRO_RATED_AMOUNT")
	private double proRatedAmount;
	
	@Column(name="KENAN_BALANCE")
	private double kenanBalance;
	
	@Column(name="WEB_BYTES")
	private double webBytes;
	
	@Column(name="WEB_MBS")
	private double webMBs;
	
	@Column(name="WEB_AMT")
	private double webAmt;
	
	@Column(name="CUST_THRESHOLD")
	private int custThreshold;
	
	@Column(name="CHG_DATE")
	private Date chgDate;
	
	@Column(name="TO_BE_NOTIFIED_AMT")
	private int toBeNotifiedAmt;
	
	@Column(name="LAST_PAY_AMOUNT")
	private double lastDayAmount;
	
	@Column(name="LAST_PAY_DATE")
	private Date lastDayDate;
	
	@Column(name="TOTAL_OVERAGE_AMT")
	private double totalOverageAmt;
	
	@Column(name="REAL_BALANCE")
	private double realBalance;
	
	@Column(name="MKT_CODE")
	private int mktCode;
	
	@Column(name="MDN_ACTIVE_DATE")
	private Date mdnActiveDate;
	
	@Column(name="MDN_INACTIVE_DATE")
	private Date mdnInactiveDate;
	
	@Column(name="DISCOUNT_AMT")
	private double discountAmt;

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getMdn() {
		return mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	public String getBillPeriod() {
		return billPeriod;
	}

	public void setBillPeriod(String billPeriod) {
		this.billPeriod = billPeriod;
	}

	public double getProRatedAmount() {
		return proRatedAmount;
	}

	public void setProRatedAmount(double proRatedAmount) {
		this.proRatedAmount = proRatedAmount;
	}

	public double getKenanBalance() {
		return kenanBalance;
	}

	public void setKenanBalance(double kenanBalance) {
		this.kenanBalance = kenanBalance;
	}

	public double getWebBytes() {
		return webBytes;
	}

	public void setWebBytes(double webBytes) {
		this.webBytes = webBytes;
	}

	public double getWebMBs() {
		return webMBs;
	}

	public void setWebMBs(double webMBs) {
		this.webMBs = webMBs;
	}

	public double getWebAmt() {
		return webAmt;
	}

	public void setWebAmt(double webAmt) {
		this.webAmt = webAmt;
	}

	public int getCustThreshold() {
		return custThreshold;
	}

	public void setCustThreshold(int custThreshold) {
		this.custThreshold = custThreshold;
	}

	public Date getChgDate() {
		return chgDate;
	}

	public void setChgDate(Date chgDate) {
		this.chgDate = chgDate;
	}

	public int getToBeNotifiedAmt() {
		return toBeNotifiedAmt;
	}

	public void setToBeNotifiedAmt(int toBeNotifiedAmt) {
		this.toBeNotifiedAmt = toBeNotifiedAmt;
	}

	public double getLastDayAmount() {
		return lastDayAmount;
	}

	public void setLastDayAmount(double lastDayAmount) {
		this.lastDayAmount = lastDayAmount;
	}

	public Date getLastDayDate() {
		return lastDayDate;
	}

	public void setLastDayDate(Date lastDayDate) {
		this.lastDayDate = lastDayDate;
	}

	public double getTotalOverageAmt() {
		return totalOverageAmt;
	}

	public void setTotalOverageAmt(double totalOverageAmt) {
		this.totalOverageAmt = totalOverageAmt;
	}

	public double getRealBalance() {
		return realBalance;
	}

	public void setRealBalance(double realBalance) {
		this.realBalance = realBalance;
	}

	public int getMktCode() {
		return mktCode;
	}

	public void setMktCode(int mktCode) {
		this.mktCode = mktCode;
	}

	public Date getMdnActiveDate() {
		return mdnActiveDate;
	}

	public void setMdnActiveDate(Date mdnActiveDate) {
		this.mdnActiveDate = mdnActiveDate;
	}

	public Date getMdnInactiveDate() {
		return mdnInactiveDate;
	}

	public void setMdnInactiveDate(Date mdnInactiveDate) {
		this.mdnInactiveDate = mdnInactiveDate;
	}

	public double getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(double discountAmt) {
		this.discountAmt = discountAmt;
	}
	
}

