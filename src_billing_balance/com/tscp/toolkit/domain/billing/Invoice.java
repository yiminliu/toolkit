package com.tscp.toolkit.domain.billing;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;




@Entity
@Table(name="bill_invoice_mvno")
 @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Invoice implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="account_no")
	private Integer accountNo;
	
	@Column(name="bill_ref_no")
	private String billRefNo;
	
	@Column(name="ACCOUNT_STATUS")
	private Integer accountStatus;
	
	@Column(name="PREP_STATUS")
	private Integer prepStatus;
	
	@Column(name="BACKOUT_STATUS")
	private Integer backoutStatus;
	
	@Column(name="PREP_ERROR_CODE")
    private Integer prepErrorCode;	
	
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="index_bill_ref")	
	private InvoiceDetail invoiceDetail;//indexBillRef;

	public InvoiceDetail getInvoiceDetail() {
		return invoiceDetail;
	}

	public void setInvoiceDetail(InvoiceDetail invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}

	public Integer getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}

	public String getBillRefNo() {
		return billRefNo;
	}

	public void setBillRefNo(String billRefNo) {
		this.billRefNo = billRefNo;
	}
		
	public Integer getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Integer getPrepStatus() {
		return prepStatus;
	}

	public void setPrepStatus(Integer prepStatus) {
		this.prepStatus = prepStatus;
	}

	public Integer getBackoutStatus() {
		return backoutStatus;
	}

	public void setBackoutStatus(Integer backoutStatus) {
		this.backoutStatus = backoutStatus;
	}

	public Integer getPrepErrorCode() {
		return prepErrorCode;
	}

	public void setPrepErrorCode(Integer prepErrorCode) {
		this.prepErrorCode = prepErrorCode;
	}

	@Override
	public String toString() {
		return "Invoice [accountNo=" + accountNo + ", invoiceDetail="
				+ invoiceDetail + "]";
	}
	
	
	
}
