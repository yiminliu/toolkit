package com.tscp.toolkit.domain.usage;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ams_billed")
public class AmsBilled extends Usage implements Serializable{

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "billed_date")
	private Date billedDate;

	public Date getBilledDate() {
		return billedDate;
	}

	public void setBilledDate(Date billedDate) {
		this.billedDate = billedDate;
	}			
}
