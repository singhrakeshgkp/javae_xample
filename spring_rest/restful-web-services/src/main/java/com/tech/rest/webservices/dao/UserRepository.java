package com.tech.rest.webservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.rest.webservices.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	
}
