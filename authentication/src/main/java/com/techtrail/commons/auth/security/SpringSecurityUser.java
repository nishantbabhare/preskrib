package com.techtrail.commons.auth.security;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SpringSecurityUser implements UserDetails{

	/**
	 * 
	 */
		
	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private Set<GrantedAuthority> authorities;
	
	private boolean accountNonExpired;
	
	private boolean accountNonLocked;
	
	private boolean credentialsNonExpired;
	
	private boolean enabled;
	
	public SpringSecurityUser( ) {
		super( );
	}
	
	public SpringSecurityUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Set<GrantedAuthority> authorities) {
		if (((username == null) || "".equals(username)) || (password == null)) { throw new IllegalArgumentException("Cannot pass null or empty values to constructor"); }
		this.username = username;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.authorities = authorities;
	}
	
	public String getUsername( ) {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Set<GrantedAuthority> getAuthorities( ) {
		return authorities;
	}
	
	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	public boolean isAccountNonExpired( ) {
		return accountNonExpired;
	}
	
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	
	public boolean isAccountNonLocked( ) {
		return accountNonLocked;
	}
	
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	
	public boolean isCredentialsNonExpired( ) {
		return credentialsNonExpired;
	}
	
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	
	public boolean isEnabled( ) {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword( ) {
		return null;
	}

	
}