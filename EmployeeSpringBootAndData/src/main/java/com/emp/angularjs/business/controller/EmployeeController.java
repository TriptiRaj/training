/**
 * 
 */
package com.emp.angularjs.business.controller;

import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.emp.angularjs.business.entity.Department;
import com.emp.angularjs.business.entity.Employee;
import com.emp.angularjs.business.entity.Organisation;


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
	public ResponseEntity<String> addEmployee(RequestEntity<Employee> employee);
	
	/**
	 * Intercepts request to update an existing employee.
	 * 
	 * @param employee		employee to update
	 */
	public void updateEmplpoyee(RequestEntity<Employee> requestEntity);
	
	/**
	 * Intercepts request to search employees on organisation and department criteria.
	 * 
	 * @param organisationId	organisation identifier
	 * @param departmentId		department identifier
	 * @return					List of employees that match the search criteria
	 */
	public ResponseEntity<List<Employee>> searchEmployee(RequestEntity<Employee> requestEntity);

	/**
	 * Intercept request to search an existing employee by its identifier
	 * 
	 * @param employeeId		employee identifier
	 * @return					employee
	 */
	public ResponseEntity<Employee> searchEmployeeById(RequestEntity<Long> employeeId);
	
	/**
	 * Intercepts request to removes an employee from DB.
	 * @param employeeId		identifier of the employee to be removed.
	 */
	public void deleteEmployee(RequestEntity<Long> requestEntity);
	
	/**
	 * Intercepts request to search Departments for a particular organisation.
	 *  
	 * @param organisationId	identifier of organisation
	 * @return					departments for organisationId
	 */
	public ResponseEntity<List<Department>>  getDepartmentsForOrganisation(RequestEntity<Long> organisationId);
	
	/**
	 * Returns Organisations.
	 */
	public ResponseEntity<List<Organisation>> getOrganisations();
}
