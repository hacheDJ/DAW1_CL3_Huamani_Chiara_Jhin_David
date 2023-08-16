package com.CL3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.CL3.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
	List<Employee> findAll();
}
