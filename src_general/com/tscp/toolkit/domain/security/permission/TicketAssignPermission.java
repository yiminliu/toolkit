package com.tscp.toolkit.domain.security.permission;

import org.springframework.security.core.Authentication;

import com.tscp.toolkit.domain.authority.ROLE;

/**
 * Permission to assign tickets to other internal users. Returns true for all users with a ROLE of Manager or greater.
 * 
 * @author Jonathan
 * 
 */
public class TicketAssignPermission extends RoleAtLeastPermission {

	@Override
	public boolean isAllowed(
			Authentication authentication, Object targetDomainObject) {

		return super.isAllowed(authentication, ROLE.ROLE_MANAGER);
	}

}
