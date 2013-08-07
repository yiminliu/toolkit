package com.tscp.toolkit.domain.billing;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name="bill_invoice_detail_mvno")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InvoiceDetail {
	
	@GenericGenerator(name="generator", strategy="foreign", parameters={@Parameter(name = "property", value = "invoice")})
	@Id
	@GeneratedValue(generator="generator")
	@Column(name="index_bill_ref")
	private String indexBillRef;
		
	@OneToOne(mappedBy="invoiceDetail")
	private Invoice invoice;
	

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	@Column(name="package_id")
	private String packageId;
	
	@Column(name="component_id")
	private String componentId;
	
	@Column(name="type_code")
	private String typeCode;
	
	@Column(name="subtype_code")
	private String subTypeCode;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="trans_date")
	private Date transDate;
	
	@Column(name="subscr_no")
	private String subscrNo;
	
	@Column(name="equip_status")
	private String equipStatus;

	public String getIndexBillRef() {
		return indexBillRef;
	}

	public void setIndexBillRef(String indexBillRef) {
		this.indexBillRef = indexBillRef;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getSubTypeCode() {
		return subTypeCode;
	}

	public void setSubTypeCode(String subTypeCode) {
		this.subTypeCode = subTypeCode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public String getSubscrNo() {
		return subscrNo;
	}

	public void setSubscrNo(String subscrNo) {
		this.subscrNo = subscrNo;
	}

	public String getEquipStatus() {
		return equipStatus;
	}

	public void setEquipStatus(String equipStatus) {
		this.equipStatus = equipStatus;
	}

	@Override
	public String toString() {
		return "InvoiceDetail [typeCode=" + typeCode + ", amount=" + amount
				+ ", transDate=" + transDate + ", equipStatus=" + equipStatus
				+ "]";
	}
	
	
}
