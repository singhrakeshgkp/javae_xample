package com.hibernate.test;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.hibernate.entity.Address;
import com.hibernate.entity.Employee;
import com.hibernate.entity.ProjectUtils;

public class SaveDataTest {

	
	public static void main(String[] args) {
	    try(Session session = ProjectUtils.getSessionFactory().openSession()) {
	    	
	    	createEmployee(session);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	  }

	private static void createEmployee(Session session) {
		session.beginTransaction();
		Integer id =(Integer)session.save(getEmployee());
		System.out.println("Employee is created  with Id::"+id);
		session.getTransaction().commit();
		
	}
	
	private static Employee getEmployee(){
		Employee employee= new Employee();
		employee.setEmployeeName("rggfgf Bingel");
		employee.setEmail("barry.cs2017@gmail.com");
		employee.setSalary(50000.00);
		employee.setDoj(new Date());
		
		Address homeAddress = new Address();
		homeAddress.setCity("jhuytt");
		homeAddress.setPincode(9087727L);
		homeAddress.setState("Tamilnadu");
		homeAddress.setStreet("Park Street");
		
		Address officeAddress = new Address();
		officeAddress.setCity("Chennai");
		officeAddress.setPincode(9087727L);
		officeAddress.setState("Tamilnadu");
		officeAddress.setStreet("Park Street");
		
		employee.getAddressSet().add(officeAddress);
		employee.getAddressSet().add(homeAddress);
		
		return employee;
	}
}
