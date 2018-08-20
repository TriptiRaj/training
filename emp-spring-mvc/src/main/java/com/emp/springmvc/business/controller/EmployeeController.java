/**
 * 
 */
package com.emp.springmvc.business.controller;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.emp.springmvc.business.entity.Department;
import com.emp.springmvc.business.entity.Employee;


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
	public ModelAndView addEmployee(final Employee employee);
	
	/**
	 * Intercepts request to update an existing employee.
	 * 
	 * @param employee		employee to update
	 */
	public String updateEmplpoyee(final Employee employee);
	
	/**
	 * Intercepts request to search employees on organisation and department criteria.
	 * 
	 * @param organisationId	organisation identifier
	 * @param departmentId		department identifier
	 * @return					List of employees that match the search criteria
	 */
	public ModelAndView searchEmployee(final Employee employee);

	/**
	 * Intercept request to search an existing employee by its identifier
	 * 
	 * @param employeeId		employee identifier
	 * @return					employee
	 */
	public ModelAndView searchEmployeeById(final Employee employee);
	
	/**
	 * Intercepts request to removes an employee from DB.
	 * @param employeeId		identifier of the employee to be removed.
	 */
	public String deleteEmployee(final Long employeeId);
	
	/**
	 * Intercepts request to search Departments for a particular organisation.
	 *  
	 * @param organisationId	identifier of organisation
	 * @return					departments for organisationId
	 */
	public List<Department> getDepartmentsForOrganisation(final Long organisationId);
	
	/**
	 * Returns Organisations.
	 */
	public List<Department> getOrganisations(Long organisationId);
}
