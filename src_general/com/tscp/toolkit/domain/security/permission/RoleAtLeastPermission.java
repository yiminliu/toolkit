package com.tscp.toolkit.domain.security.permission;

import org.springframework.security.core.Authentication;

import com.tscp.toolkit.domain.authority.ROLE;
import com.tscp.toolkit.domain.user.User;
//import com.trc.user.authority.ROLE;

public class RoleAtLeastPermission extends Permission {

	@Override
	public boolean isAllowed(
			Authentication authentication, Object targetDomainObject) {

		boolean hasPermission = false;

		if (isAuthenticated(authentication)) {
			User user = (User) authentication.getPrincipal();
			String role = (String) targetDomainObject;
			hasPermission = isAllowed(user, ROLE.valueOf(role));
		}

		return hasPermission;
	}

	protected boolean isAllowed(
			User user, ROLE role) {
		return true; //user.getGreatestAuthority().compare(role) >= 0;
	}

}