package com.tech.security.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UserEntity {

	private String id;
	private String name;
	private String password;
	
	private String emailId;
	
	private Collection<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<GrantedAuthority> getGrantedAuthorityList() {
		return grantedAuthorityList;
	}
	public void setGrantedAuthorityList(Collection<GrantedAuthority> grantedAuthorityList) {
		this.grantedAuthorityList = grantedAuthorityList;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
}