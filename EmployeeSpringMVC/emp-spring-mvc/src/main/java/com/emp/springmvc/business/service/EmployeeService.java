/**
 * 
 */
package com.emp.springmvc.business.service;

import java.util.List;

import com.emp.springmvc.business.entity.Department;
import com.emp.springmvc.business.entity.Employee;
import com.emp.springmvc.business.entity.Organisation;



/**
 * @author Tripti
 *
 */
public interface EmployeeService {
	
	/**
	 * Calls the data access method to add a new Employee.
	 * 
	 * @param employee			employee to insert
	 */
	public void addEmployee(final Employee employee);
	
	/**
	 * Calls the data access method to update existing employee's details.
	 * 
	 * @param employee			employee to update
	 */
	public void updateEmplpoyee(final Employee employee);
	
	/**
	 * Calls the data access method to search employees on organisation and department criteria.
	 * 
	 * @param organisationId	organisation identifier
	 * @param departmentId		department identifier
	 * @return					List of employees that match the search criteria
	 */
	public List<Employee> searchEmployee(final Long organisationId, final Long departmentId);

	/**
	 * Calls the data access method to search an existing employee by its identifier
	 * 
	 * @param employeeId		employee identifier
	 * @return					employee
	 */
	public Employee searchEmployeeById(final Long employeeId);
	
	/**
	 * Calls data access method to remove an employee.
	 * 
	 * @param employeeId		identifier of the employee to be removed.
	 */
	public void deleteEmployee(final Long employeeId);
	
	/**
	 * Calls data access method to search Departments for a particular organisation. 
	 * 
	 * @param organisationId	identifier of organisation
	 * @return					departments for organisationId
	 */
	public List<Department> getDepartmentsForOrganisation(final Long organisationId);
	
	/**
	 * Returns Organisations.
	 */
	public List<Organisation> getOrganisations();
}
