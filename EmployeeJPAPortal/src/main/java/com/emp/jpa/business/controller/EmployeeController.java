/**
 * 
 */
package com.emp.jpa.business.controller;

import java.util.List;

import com.emp.jpa.business.entity.Department;
import com.emp.jpa.business.entity.Employee;


/**
 * @author Tripti
 *
 */
public interface EmployeeController {
	
	public void addEmployee(final Employee employee);
	
	public void updateEmplpoyee(final Employee employee);
	
	public List<Employee> searchEmployee(final Long organisationId, final Long departmentId);

	public Employee searchEmployeeById(final Long employeeId);
	
	public void deleteEmployee(final Long employeeId);
	
	public List<Department> getDepartmentsForOrganisation(final Long organisationId);

}
