package com.tscp.toolkit.dao;

import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tscp.toolkit.domain.device.DeviceInfo;

@Repository
public interface IDeviceDao {
		
	@Transactional(readOnly=true)
	public Set<DeviceInfo> getAllDevices();
	
	@Transactional(readOnly=true)
    public Set<DeviceInfo> getActiveAndSusppendedDevices();
	
	@Transactional(readOnly=true)
	public Set<DeviceInfo> getActiveDevices();
	
}
