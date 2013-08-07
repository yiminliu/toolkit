package com.tscp.toolkit.domain.user;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import com.tscp.toolkit.domain.authority.Authority;
import com.tscp.toolkit.domain.authority.ROLE;


@Entity
@Table(name="users")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements UserDetails,  Serializable {
	private static final long serialVersionUID = -6398259652301626438L;
	
	private String id;	
	private String username;	
	private String email;
	private String password;	
	//@Column(name="question_id")
	//private String questionId;
	private String hintId;	
	//@Column(name="question_answer")
    //private String questionAnswer;	
    private String hintAnswer;
	private boolean enabled;	
	private Date dateEnabled;
	private Date dateDisabled;	
	private String newPassword;
	private String newEmail;
	private String action;
	private Set<GrantedAuthority> grantedAuthorities;	
	private Collection<Authority> authorities = new HashSet<Authority>();
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="hint_id")
	public String getHintId() {
		return hintId;
	}

	public void setHintId(String hintId) {
		this.hintId = hintId;
	}

	@Column(name="hint_answer")
	public String getHintAnswer() {
		return hintAnswer;
	}

	public void sethintAnswer(String hintAnswer) {
		this.hintAnswer = hintAnswer;
	}

	@Column(name = "enabled", columnDefinition = "BOOLEAN")
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name="date_enabled")
	public Date getDateEnabled() {
		return dateEnabled;
	}

	public void setDateEnabled(Date dateEnabled) {
		this.dateEnabled = dateEnabled;
	}

	@Column(name="date_disabled")
	public Date getDateDisabled() {
		return dateDisabled;
	}

	public void setDateDisabled(Date dateDisabled) {
		this.dateDisabled = dateDisabled;
	}
		
	@Transient
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
		
	@Transient
	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	public void setHintAnswer(String hintAnswer) {
		this.hintAnswer = hintAnswer;
	}

	@Transient
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}	

	//public Collection<Authority> getAuthorities() {
	//	return authorities;
	//}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	public Collection<Authority> getRoles() {
		return this.authorities;
	}

	public void setRoles(
			Collection<Authority> roles) {
		this.authorities = roles;
	}

	/********************************************************************************************************************************
	 * Begins Spring UserDetails implementation methods. User class should not implement UserDetails, instead we should
	 * create another class that uses Assembler to keep our design separate from Spring.
	 ********************************************************************************************************************************/
		
	
	//@OneToMany(fetch=FetchType.EAGER, mappedBy="user")
	@Transient
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public Set<GrantedAuthority> getAuthorities() {
		if (grantedAuthorities == null) {
			grantedAuthorities = new HashSet<GrantedAuthority>();
			for (Authority authority : getRoles()) {
				grantedAuthorities.add(new GrantedAuthorityImpl(authority.getRole().toString()));
			}
		}
		return grantedAuthorities;
	}
		
	@Transient
	public Authority getGreatestAuthority() {
		Authority greatestRole = new Authority(ROLE.ROLE_ANONYMOUS);
		for (Authority auth : getRoles())
			if (greatestRole.compare(auth) < 0)
				greatestRole = auth;
		return greatestRole;
	}
	
	@Transient
	public boolean isInternalUser() {
		Set<ROLE> internalRoles = new HashSet<ROLE>(Arrays.asList(ROLE.getInternalRoles()));

		for (Authority auth : getRoles())
			if (internalRoles.contains(auth.getRole()))
				return true;

		return false;
	}
	
	public void setAuthorities(Collection<Authority> authorities) {
		this.authorities = authorities;
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return isEnabled();
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return isEnabled();
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return isEnabled();
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + username + ", email=" + email
				+ ", password=" + password + ", hindId=" + hintId
				+ ", hintAnswer=" + hintAnswer + ", enabled=" + enabled
				+ ", dateEnabled=" + dateEnabled + ", dateDisabled="
				+ dateDisabled + ", authorities=" + authorities + "]";
	}
	
	
	
}
