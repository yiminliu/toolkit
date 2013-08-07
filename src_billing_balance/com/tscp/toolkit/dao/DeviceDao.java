package com.tscp.toolkit.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.eclipse.persistence.annotations.ReadOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tscp.toolkit.domain.device.DeviceInfo;

@Repository
public class DeviceDao extends HibernateDaoSupport implements IDeviceDao{
			
	@Autowired
	@Qualifier("hibernateTemplateOracle")
	//@PostConstruct
	public void init(HibernateTemplate hibernateTemplate){
		setHibernateTemplate(hibernateTemplate);
		hibernateTemplate = getHibernateTemplate();
	}
	
	public DeviceDao(){}
	
	@Override
	@Transactional(readOnly=true)
	public Set<DeviceInfo> getAllDevices(){		
		String queryString = "from DeviceInfo ";		
		List deviceList = (List<DeviceInfo>)getHibernateTemplate().find(queryString);
		return new HashSet<DeviceInfo>(deviceList);		
	}
	
	@Override
	@Transactional(readOnly=true)
    public Set<DeviceInfo> getActiveAndSusppendedDevices(){
		/*String queryString = "from DeviceInfo di " +
				             "where di.custId > 0 and " +
				             "di.accountNo is not null and " +
				             "di.statusId in (2,5) and " +
				             "di.accountNo in ( " +
				             "select distinct bm.accountNo " +
				             "from BmfMvno bm " +
				             "where bm.noBill = 0) ";
				             //"di.accountNo = bm.accountNo ";
				              
				              */
		String queryString = "select di from DeviceInfo di, CmfMvno cm " +
        "where di.custId > 0 and " +
        "di.accountNo is not null and " +
        "di.statusId in (2,5) and " +
        "cm.noBill = 0 and " +
        "di.accountNo = cm.accountNo ";
		List deviceList = (List<DeviceInfo>)getHibernateTemplate().find(queryString);
		return new HashSet<DeviceInfo>(deviceList);		
	}	
	
	@Override
	@Transactional(readOnly=true)
    public Set<DeviceInfo> getActiveDevices(){
		/*String queryString = "from DeviceInfo di " +
				             "where di.custId > 0 and " +
				             "di.accountNo is not null and " +
				             "di.statusId in (2,5) and " +
				             "di.accountNo in ( " +
				             "select distinct bm.accountNo " +
				             "from BmfMvno bm " +
				             "where bm.noBill = 0) ";
				             //"di.accountNo = bm.accountNo ";
				              
				              */
		String queryString = "select di from DeviceInfo di, CmfMvno cm " +
        "where di.custId > 0 and " +
        "di.accountNo is not null and " +
        "di.statusId = 2 and " +
        "cm.noBill = 0 and " +
        "di.accountNo = cm.accountNo ";
		List deviceList = (List<DeviceInfo>)getHibernateTemplate().find(queryString);
		return new HashSet<DeviceInfo>(deviceList);		
	}	
	
}
