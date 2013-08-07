package com.tscp.toolkit.domain.balance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Balance_Audit")
public class BalanceAudit implements Serializable {
	
	private Integer accountNo;
		
	private Integer custId;
		
	private Float kenanRealBalance;
		
	private Float dynamicBalance;
		
	private Date timeStamp;

	@Id
	@Column(name = "Account_No")
	public Integer getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}

	@Column(name = "Cust_Id")
	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	@Column(name = "Kenan_Real_Balance")
	public Float getKenanRealBalance() {
		return kenanRealBalance;
	}

	public void setKenanRealBalance(Float kenanRealBalance) {
		this.kenanRealBalance = kenanRealBalance;
	}

	@Column(name = "Dynamic_Balance")
	public Float getDynamicBalance() {
		return dynamicBalance;
	}

	public void setDynamicBalance(Float dynamicBalance) {
		this.dynamicBalance = dynamicBalance;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Timestamp")
	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountNo == null) ? 0 : accountNo.hashCode());
		result = prime * result + ((custId == null) ? 0 : custId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BalanceAudit other = (BalanceAudit) obj;
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		if (custId == null) {
			if (other.custId != null)
				return false;
		} else if (!custId.equals(other.custId))
			return false;
		return true;
	}	
}
