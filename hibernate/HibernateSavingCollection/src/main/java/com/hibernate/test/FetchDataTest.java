package com.hibernate.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.hibernate.entity.Employee;
import com.hibernate.entity.ProjectUtils;

public class FetchDataTest {

	public static void main(String[] args) {
		
		Employee employee = null;
		try(Session session = ProjectUtils.getSessionFactory().openSession()){
			employee = session.get(Employee.class, 1);
			System.out.println(employee);
			//System.out.println(employee.getAddress());
		}catch(HibernateException e) {
			e.printStackTrace();
		}
	}
}
