package com.tscp.toolkit.domain.security.permission;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tscp.toolkit.domain.user.User;
import com.tscp.toolkit.domain.authority.Authority;
import com.tscp.toolkit.domain.authority.ROLE;

public abstract class Permission {
	protected final Set<ROLE> roleRepository = new HashSet<ROLE>();

	{
		roleRepository.add(ROLE.ROLE_SU);
	}

	public abstract boolean isAllowed(
			Authentication authentication, Object targetDomainObject);

	protected String getLogin(
			Authentication authentication) {
		return ((UserDetails) authentication.getPrincipal()).getUsername();
	}

	protected Collection<GrantedAuthority> getAuthorities(
			Authentication authentication) {
		return ((UserDetails) authentication.getPrincipal()).getAuthorities();
	}

	protected boolean isAuthenticated(
			Authentication authentication) {
		return authentication != null && authentication.getPrincipal() instanceof UserDetails;
	}

	protected boolean isRoleGrantedPermission(
			User user) {
		for (Authority authority : user.getRoles())
			if (roleRepository.contains(authority.getRole()))
				return true;
		return false;
	}

}