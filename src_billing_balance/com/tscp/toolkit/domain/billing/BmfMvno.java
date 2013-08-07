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
@Table(name="bmf_mvno")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BmfMvno implements Serializable{
		
	private Integer billRefNo;	
	private Integer accountNo;	
	private Integer origBillRefNo;	
	private Integer bmfTransType;	
	private Date transDate;	
	private Date postDate;	
	private double transAmount;	
	private double glAmount;	
	private Integer trackingId;
	private Integer transDource;	
	private String transSubmitter;
	private Date chgDate;	
	private Integer noBill;
	private String actionCode;
	private double externalAmount;

	@Id
	@Column(name="BILL_REF_NO")
	public Integer getBillRefNo() {
		return billRefNo;
	}

	public void setBillRefNo(Integer billRefNo) {
		this.billRefNo = billRefNo;
	}

	@Column(name="ACCOUNT_NO")
	public Integer getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}

	@Column(name="ORIG_BILL_REF_NO")
	public Integer getOrigBillRefNo() {
		return origBillRefNo;
	}

	public void setOrigBillRefNo(Integer origBillRefNo) {
		this.origBillRefNo = origBillRefNo;
	}

	@Column(name="BMF_TRANS_TYPE")
	public Integer getBmfTransType() {
		return bmfTransType;
	}

	public void setBmfTransType(Integer bmfTransType) {
		this.bmfTransType = bmfTransType;
	}

	@Column(name="TRANS_DATE")
	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	@Column(name="POST_DATE")
	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}


	@Column(name="TRANS_AMOUNT")
	public double getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(double transAmount) {
		this.transAmount = transAmount;
	}

	@Column(name="GL_AMOUNT")
	public double getGlAmount() {
		return glAmount;
	}

	public void setGlAmount(double glAmount) {
		this.glAmount = glAmount;
	}


	@Column(name="TRACKING_ID")
	public Integer getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(Integer trackingId) {
		this.trackingId = trackingId;
	}


	@Column(name="TRANS_SOURCE")
	public Integer getTransDource() {
		return transDource;
	}

	public void setTransDource(Integer transDource) {
		this.transDource = transDource;
	}


	@Column(name="TRANS_SUBMITTER")
	public String getTransSubmitter() {
		return transSubmitter;
	}

	public void setTransSubmitter(String transSubmitter) {
		this.transSubmitter = transSubmitter;
	}

	@Column(name="CHG_DATE")
	public Date getChgDate() {
		return chgDate;
	}

	public void setChgDate(Date chgDate) {
		this.chgDate = chgDate;
	}

	@Column(name="NO_BILL")
	public Integer getNoBill() {
		return noBill;
	}

	public void setNoBill(Integer noBill) {
		this.noBill = noBill;
	}

	@Column(name="ACTION_CODE")
	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}


	@Column(name="EXTERNAL_AMOUNT")
	public double getExternalAmount() {
		return externalAmount;
	}

	public void setExternalAmount(double externalAmount) {
		this.externalAmount = externalAmount;
	}
	
	
	
}
