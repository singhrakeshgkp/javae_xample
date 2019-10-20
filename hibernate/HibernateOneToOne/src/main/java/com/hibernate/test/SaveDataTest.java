package com.hibernate.test;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.hibernate.entity.Address;
import com.hibernate.entity.Employee;
import com.hibernate.entity.ProjectUtils;

public class SaveDataTest {

	/*
	 * Cascade property is used to save child details as well like session.save(employee) and session.save(address)
	 * if we do not specify the @JoiningTable(name ="column_name") then hibernate will use the reference variable name of type address
	 *  which we have in employee class and append it in address primary key column name, that will we our foreign key.
	 *  =>This is also known as unidirection one to one mapping
	 *  
	 * */
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
		employee.setEmployeeName("Barry Bingel");
		employee.setEmail("barry.cs2017@gmail.com");
		employee.setSalary(50000.00);
		employee.setDoj(new Date());
		
		Address address1 = new Address();
		address1.setCity("Chennai");
		address1.setPincode(9087727L);
		address1.setState("Tamilnadu");
		address1.setStreet("Park Street");
		
		employee.setAddress(address1);
		
		return employee;
	}
}
