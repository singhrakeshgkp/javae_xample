package com.tech.security.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

	private User user;

	public UserPrincipal(User user) {
		this.user = user;

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		// Extract list of permissions
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		this.user.getPermissionList().forEach(p -> {

			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(p);
			authorities.add(grantedAuthority);

		});

		// Extract list of role

		this.user.getRoleList().forEach(r -> {

			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+r);
			authorities.add(grantedAuthority);

		});
		return authorities;
	}

	@Override
	public String getPassword() {

		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.user.getActive() == 1 ? true : false;
	}

}
