package com.tscp.toolkit.domain.billing;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="cmf_mvno")
 @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CmfMvno implements Serializable{
		
	@Id
	@Column(name="ACCOUNT_NO")
	private Integer accountNo;
	
	@Column(name="LANGUAGE_CODE")
	private Integer languageCode;
	
	@Column(name="ACCOUNT_TYPE")
	private Integer accountType;
	
	@Column(name="ACCOUNT_CATEGORY")
	private Integer accountCategory;
	
	@Column(name="BILL_FNAME")
	private String billFirstName;
	
	@Column(name="BILL_LNAME")
	private String billLastName;
	
	@Column(name="ACCOUNT_STATUS")
	private Integer accountStatus;
	
	@Column(name="CUST_EMAIL")
	private String custEmail;
	
	@Column(name="DATE_CREATED")
	private Date createdDate;
	
	@Column(name="DATE_ACTIVE")
	private Date activeDate;
	
	@Column(name="DATE_INACTIVE")
	private Date inactiveDate;
	
	@Column(name="CHARGE_THRESHOLD")
	private Integer chargeThreshold;
	
	@Column(name="THRESHOLD")
	private Integer threshold;
		
	@Column(name="CHG_DATE")
	private Date chgDate;
	
	@Column(name="NO_BILL")
	private Integer noBill;

	public Integer getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}

	public Integer getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(Integer languageCode) {
		this.languageCode = languageCode;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getAccountCategory() {
		return accountCategory;
	}

	public void setAccountCategory(Integer accountCategory) {
		this.accountCategory = accountCategory;
	}

	public String getBillFirstName() {
		return billFirstName;
	}

	public void setBillFirstName(String billFirstName) {
		this.billFirstName = billFirstName;
	}

	public String getBillLastName() {
		return billLastName;
	}

	public void setBillLastName(String billLastName) {
		this.billLastName = billLastName;
	}

	public Integer getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getActiveDate() {
		return activeDate;
	}

	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
	}

	public Date getInactiveDate() {
		return inactiveDate;
	}

	public void setInactiveDate(Date inactiveDate) {
		this.inactiveDate = inactiveDate;
	}

	public Integer getChargeThreshold() {
		return chargeThreshold;
	}

	public void setChargeThreshold(Integer chargeThreshold) {
		this.chargeThreshold = chargeThreshold;
	}

	public Integer getThreshold() {
		return threshold;
	}

	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	public Date getChgDate() {
		return chgDate;
	}

	public void setChgDate(Date chgDate) {
		this.chgDate = chgDate;
	}

	public Integer getNoBill() {
		return noBill;
	}

	public void setNoBill(Integer noBill) {
		this.noBill = noBill;
	}	
}
