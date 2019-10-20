package com.tech.security.repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.tech.security.model.UserEntity;

@Repository
public class UserDetailsImpl implements UserDetailsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public UserEntity getUserDetails(String emailId) {
		
		Collection<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
		List<UserEntity> list = jdbcTemplate.query("select * from user where email_id=?",new String[] {emailId},(ResultSet rs, int rowNumber)->{
			
		UserEntity user = new UserEntity();
		user.setEmailId(emailId);
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		return user;
		});
		
		if(!list.isEmpty()) {
			
			UserEntity userEntity = list.get(0);
			List<String> permissionList = jdbcTemplate.query("select distinct p.permission_name from permission p \r\n"+
			"inner join assign_permission_to_role p_r on p.id = p_r.permission_id \r\n"+
			"inner join role r on r.id = p_r.role_id \r\n"+
			"inner join assign_user_to_role u_r on u_r.role_id = r.id \r\n"+
			"inner join user u on u.id = u_r.user_id \r\n"+
			"where u.email_id = ?" , new String[] {emailId}, (ResultSet rs, int rowNumber)->{
				
				return "ROLE_"+rs.getString("permission_name");
			});
			
			if(permissionList != null && !permissionList.isEmpty()) {
				permissionList.forEach(userRole->{
					GrantedAuthority authority = new SimpleGrantedAuthority(userRole);
					grantedAuthorityList.add(authority);
				});
				
			list.get(0).setGrantedAuthorityList(grantedAuthorityList);
			}
			return list.get(0);
		}
		return null;
	}

}
