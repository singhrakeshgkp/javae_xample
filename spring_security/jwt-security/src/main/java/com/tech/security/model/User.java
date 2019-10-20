package com.tech.security.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String userName;

	@Column(nullable = false)
	private String password;

	private int blocked;

	private int active;

	private String roles;

	private String permissions;

	public User(String userName, String password, String roles, String permissions) {
		super();
		this.userName = userName;
		this.password = password;
		this.roles = roles;
		this.permissions = permissions;
		this.active = 1;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getBlocked() {
		return blocked;
	}

	public void setBlocked(int blocked) {
		this.blocked = blocked;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public List<String> getRoleList() {

		if (this.roles.length() > 0) {

			return Arrays.asList(roles.split(","));
		}
		return new ArrayList<>();
	}

	public List<String> getPermissionList() {

		if (this.getPermissions().length() > 0) {

			return Arrays.asList(permissions.split(","));
		}
		return new ArrayList<>();
	}
	
	protected User() {
		
		
	}
}
