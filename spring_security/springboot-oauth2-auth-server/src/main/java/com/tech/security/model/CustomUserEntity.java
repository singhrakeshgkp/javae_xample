package com.tech.security.model;

import org.springframework.security.core.userdetails.User;

public class CustomUserEntity extends User {

	
	private String id;
	private String name;
	
	public CustomUserEntity(UserEntity userEntity) {
		
		super(userEntity.getEmailId(),userEntity.getPassword(),userEntity.getGrantedAuthorityList());
		this.id = userEntity.getId();
		this.name = userEntity.getName();
	}

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

	
}
