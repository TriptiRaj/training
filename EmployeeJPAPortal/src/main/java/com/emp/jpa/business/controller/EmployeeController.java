/**
 * 
 */
package com.emp.jpa.business.controller;

import java.util.List;

import com.emp.jpa.business.entity.Department;
import com.emp.jpa.business.entity.Employee;


/**
 * Controller Interface for EmployeeJpaPotal application.
 * 
 * @author Tripti
 */
public interface EmployeeController {
	
	/**
	 * Intercepts the request for adding a new employee.
	 * 
	 * @param employee		employee to insert
	 */
	public void addEmployee(final Employee employee);
	
	/**
	 * Intercepts request to update an existing employee.
	 * 
	 * @param employee		employee to update
	 */
	public void updateEmplpoyee(final Employee employee);
	
	/**
	 * Intercepts request to search employees on organisation and department criteria.
	 * 
	 * @param organisationId	organisation identifier
	 * @param departmentId		department identifier
	 * @return					List of employees that match the search criteria
	 */
	public List<Employee> searchEmployee(final Long organisationId, final Long departmentId);

	/**
	 * Intercept request to search an existing employee by its identifier
	 * 
	 * @param employeeId		employee identifier
	 * @return					employee
	 */
	public Employee searchEmployeeById(final Long employeeId);
	
	/**
	 * Intercepts request to removes an employee from DB.
	 * @param employeeId		identifier of the employee to be removed.
	 */
	public void deleteEmployee(final Long employeeId);
	
	/**
	 * Intercepts request to search Departments for a particular organisation.
	 *  
	 * @param organisationId	identifier of organisation
	 * @return
	 */
	public List<Department> getDepartmentsForOrganisation(final Long organisationId);
}
