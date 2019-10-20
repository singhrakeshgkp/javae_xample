package com.hibernate.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.hibernate.entity.Employee;
import com.hibernate.entity.ProjectUtils;

public class FetchDataTest {

	public static void main(String[] args) {
		
		Employee employee = null;
		try(Session session = ProjectUtils.getSessionFactory().openSession()){
			
			List<Employee> list = session.createNativeQuery("select * from employee_table",Employee.class).list();
		     
			System.out.println(list.get(0));
			//System.out.println(list.get(0).getAddress());
						
		}catch(HibernateException e) {
			e.printStackTrace();
		}
	}
}
