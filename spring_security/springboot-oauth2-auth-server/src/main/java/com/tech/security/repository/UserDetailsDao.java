package com.tech.security.repository;

import com.tech.security.model.UserEntity;

public interface UserDetailsDao {

	public UserEntity getUserDetails(String emailId);
}
