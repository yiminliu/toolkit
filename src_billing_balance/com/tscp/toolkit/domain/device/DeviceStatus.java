package com.tscp.toolkit.domain.device;

import java.io.Serializable;

public enum DeviceStatus implements Serializable{
	NEW(1),
	ACTIVE(2),
	DEACTIVATED(3),
	REMOVED(4),
	SUSPENDED(5),
	BLOCKED(6);
	
	private int statusId;

	private DeviceStatus(int statusId) {
		this.statusId = statusId;
	}

	public int getStaus() {
		return statusId;
	}
	
	public String toString(){
		String status = "Unkown";
		switch(this) {
		case NEW:
			status = "New";
			break;
		case ACTIVE:
			status = "Active";
			break;
		case DEACTIVATED:
			status = "Deactivated";
			break;
		case REMOVED:
			status = "Removed";
			break;
		case SUSPENDED:
			status = "Suspended";
			break;
		case BLOCKED:
			status = "Blocked";
			break;
		}
	
	    return status;
	}
}