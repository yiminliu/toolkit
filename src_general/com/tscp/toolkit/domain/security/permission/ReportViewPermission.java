package com.tscp.toolkit.domain.security.permission;

import org.springframework.security.core.Authentication;

import com.tscp.toolkit.domain.authority.ROLE;

//import com.trc.user.authority.ROLE;

public class ReportViewPermission extends RoleAtLeastPermission {

	@Override
	public boolean isAllowed(
			Authentication authentication, Object targetDomainObject) {

		return super.isAllowed(authentication, ROLE.ROLE_MANAGER);
	}
}
