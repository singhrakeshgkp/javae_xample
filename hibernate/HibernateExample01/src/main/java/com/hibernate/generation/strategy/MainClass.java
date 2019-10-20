package com.hibernate.generation.strategy;

import java.util.Date;

import org.hibernate.Session;

import com.hibernate.config.ProjectUtils;

/*
 *1. The default generation strategy is auto, in hibernate 5 it creates a new table named hibernate_sequence, we can customize the
 *name of this table 
 *2. Identity Generation Type :- for this spring 4 generate new table but in spring 5 id do not generate new table named sequence
 *3.  
 * 
 * 
 * */
public class MainClass {

	public static void main(String[] args) {
	
		try(Session session = ProjectUtils.getSessionFactory().openSession()){
			
			session.beginTransaction();
			Employee emp = new Employee();
			emp.setDoj(new Date());
			emp.setEmployeeName("emp test");
			Integer id =  (Integer) session.save(emp);
			System.out.println("Saved Emp Id :- "+id);
			session.getTransaction().commit();
		}
	}
}
