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
@Table(name="charge_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChargeHistory implements Serializable{
		
	private Integer accountNo;
	private Date dateAndTime;	
	private String type;	
	private Integer usage;	
	private Float rate;
	private Float amount;	
	private Float discount;
	private Date startTime;	
	private Date endTime;
	private String notes;	 
	private String externalId;

	@Id
	@Column(name="account_no")
	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	
	@Id
	@Column(name="date_and_time")
	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	@Column(name="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name="usage")
	public int getUsage() {
		return usage;
	}

	public void setUsage(int usage) {
		this.usage = usage;
	}

	@Column(name="rate")
	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	@Column(name="amount")
	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}


	@Column(name="discount")
	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	@Column(name="start_time")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name="end_time")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name="notes")
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	 
	@Column(name="external_id")
	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNo;
		result = prime * result
				+ ((dateAndTime == null) ? 0 : dateAndTime.hashCode());
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
		ChargeHistory other = (ChargeHistory) obj;
		if (accountNo != other.accountNo)
			return false;
		if (dateAndTime == null) {
			if (other.dateAndTime != null)
				return false;
		} else if (!dateAndTime.equals(other.dateAndTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ChargeHistory [accountNo=" + accountNo + ", dateAndTime="
				+ dateAndTime + ", type=" + type + ", usage=" + usage
				+ ", rate=" + rate + ", amount=" + amount + ", discount="
				+ discount + ", startTime=" + startTime + ", endTime="
				+ endTime + ", notes=" + notes + ", externalId=" + externalId
				+ "]";
	}
				
}
