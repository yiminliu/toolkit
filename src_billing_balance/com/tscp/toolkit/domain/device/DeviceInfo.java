package com.tscp.toolkit.domain.device;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="device_info")
public class DeviceInfo implements Serializable{
	
	@Id
	@Column(name="DEVICE_ID")
	private Integer deviceId;
	
	@Column(name="CUST_ID")
	private Integer custId;
	
	@Column(name="ACCOUNT_NO")
	private Integer accountNo;
	
	@Column(name="DEVICE_LABEL")
	private String deviceLable;
	
	@Column(name="DEVICE_VALUE")
	private String deviceValue;
	
	@Column(name="STATUS_ID")
	private Integer statusId;
	
	@Column(name="MOD_DATE")
	private Date modDate;
	
	@Column(name="EFF_DATE")
	private Date effDate;
	
	@Column(name="EXP_DATE")
	private Date expDate;

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Integer getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}

	public String getDeviceLable() {
		return deviceLable;
	}

	public void setDeviceLable(String deviceLable) {
		this.deviceLable = deviceLable;
	}

	public String getDeviceValue() {
		return deviceValue;
	}

	public void setDeviceValue(String deviceValue) {
		this.deviceValue = deviceValue;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deviceId == null) ? 0 : deviceId.hashCode());
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
		DeviceInfo other = (DeviceInfo) obj;
		if (deviceId == null) {
			if (other.deviceId != null)
				return false;
		} else if (!deviceId.equals(other.deviceId))
			return false;
		return true;
	}
	
	
}	