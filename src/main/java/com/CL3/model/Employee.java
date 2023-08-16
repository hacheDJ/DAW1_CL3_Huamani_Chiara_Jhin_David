package com.CL3.model;

import java.math.BigDecimal;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name_employee")
	private String name;
	
	@Column(name = "lastname")
	private String lastName;
	
	private BigDecimal salary;
	


	@Column(name = "position_employee")
	private String position;
	
	@CreationTimestamp
	@Column(name = "register_date")
	private String registerDate;
	
	
	public Employee() {}

	

	public Employee(String nameEmployee, String lastName, BigDecimal salary, String position, String registerDate) {
		this.name = nameEmployee;
		this.lastName = lastName;
		this.salary = salary;
		this.position = position;
		this.registerDate = registerDate;
	}



	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public BigDecimal getSalary() {
		return salary;
	}


	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getRegisterDate() {
		return registerDate;
	}


	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	
	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
}
