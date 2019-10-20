package com.hibernate.entity;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name="employee_table")
@DynamicUpdate
public class Employee {

	@Id
	@Column(name="employee_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer employeeId;
	
	@Column(name="employee_name",length=200,nullable=false)
	private String employeeName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="date_of_joing")
	private Date doj;
	
	@Column(name="salary")
	private Double salary;
	
	@ElementCollection
	@JoinTable(name="emp_address",joinColumns=@JoinColumn(name="emp_id"))
	//Till now we were not generating unique id for address.
	//Now we're gonna add some annotation to generate unique id for address table,
	@GenericGenerator(name="sequence-gen",strategy="sequence")
	@CollectionId(columns= {@Column(name="address_id")},generator="sequence-gen", type=@Type(type="int"))
	private Collection<Address> addressSet = new HashSet<>();
	
	
	
	
	
	public Collection<Address> getAddressSet() {
		return addressSet;
	}
	public void setAddressSet(Collection<Address> addressSet) {
		this.addressSet = addressSet;
	}
	
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", email=" + email + ", doj="
				+ doj + ", salary=" + salary + "]";
	}
}