package com.tscp.toolkit.domain.usage;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@MappedSuperclass
public abstract class Usage implements Serializable{

	@Id
	@Column(name="FILENAME")
	private String fileName;
	
	@Column(name="ACCT_STATUS")
	private String accountStatus;
	   
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="TSCP_ACCOUNT_NO")
	private Integer accountNo;
	
	@Column(name="TSCP_SUBSCR_NO")
	private Integer subscrNo;
			
	@Column(name="TSCP_COMPONENT_ID")
	private Integer componentId;
	
	@Column(name="TSCP_RATE")
	private Integer rate;
	
	@Column(name="TSCP_SESSION_BYTES")
	private Integer sessionBytes;
	
	@Column(name="TSCP_SESSION_MBS")
	private Integer sessionMBs;
	
	@Column(name="TSCP_DOLLAR_CHARGE")
	private Double dollarCharge;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EVENT_TIMESTAMP")
	private Date eventTimestamp;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TSCP_ACTUAL_TIMESTAMP")
	private Date tscpActualTimestamp;
	
	@Column(name="ACCT_OUTPUT")
	private int output;
	
	@Column(name="ACCT_INPUT")
	private int input;

	@Transient
	private float sumDollarCharge;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}

	public Integer getSubscrNo() {
		return subscrNo;
	}

	public void setSubscrNo(Integer subscrNo) {
		this.subscrNo = subscrNo;
	}

	public Integer getComponentId() {
		return componentId;
	}

	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getSessionBytes() {
		return sessionBytes;
	}

	public void setSessionBytes(Integer sessionBytes) {
		this.sessionBytes = sessionBytes;
	}

	public Integer getSessionMBs() {
		return sessionMBs;
	}

	public void setSessionMBs(Integer sessionMBs) {
		this.sessionMBs = sessionMBs;
	}

	public double getDollarCharge() {
		return dollarCharge;
	}

	public void setDollarCharge(double dollarCharge) {
		this.dollarCharge = dollarCharge;
	}

	public Date getEventTimestamp() {
		return eventTimestamp;
	}

	public void setEventTimestamp(Date eventTimestamp) {
		this.eventTimestamp = eventTimestamp;
	}

	public Date getActualTimestamp() {
		return tscpActualTimestamp;
	}

	public void setActualTimestamp(Date tscpActualTimestamp) {
		this.tscpActualTimestamp = tscpActualTimestamp;
	}

	public int getOutput() {
		return output;
	}

	public void setOutput(int output) {
		this.output = output;
	}

	public int getInput() {
		return input;
	}

	public void setInput(int input) {
		this.input = input;
	}

	public float getSumDollarCharge() {
		return sumDollarCharge;
	}

	public void setSumDollarCharge(float sumDollarCharge) {
		this.sumDollarCharge = sumDollarCharge;
	}

	public void setDollarCharge(Double dollarCharge) {
		this.dollarCharge = dollarCharge;
	}	
	
}
