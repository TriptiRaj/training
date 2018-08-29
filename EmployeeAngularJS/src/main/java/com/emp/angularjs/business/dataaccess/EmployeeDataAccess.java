/**
 * 
 */
package com.emp.angularjs.business.dataaccess;

import java.util.List;

import com.emp.angularjs.business.entity.Department;
import com.emp.angularjs.business.entity.Employee;
import com.emp.angularjs.business.entity.Organisation;



/**
 * @author Tripti
 *
 */
public interface EmployeeDataAccess {
	
	/**
	 * Insert new Employee details in DB.
	 * 
	 * @param employee		employee to insert
	 */
	public void addEmployee(final Employee employee);
	
	/**
	 * Updates existing employee's details in DB.
	 * 
	 * @param employee		employee to update
	 */
	public void updateEmplpoyee(final Employee employee);
	
	/**
	 * Search employees on organisation and department criteria.
	 * 
	 * @param organisationId	organisation identifier
	 * @param departmentId		department identifier
	 * @return					List of employees that match the search criteria
	 */
	public List<Employee> searchEmployee(final Long organisationId, final Long departmentId);

	/**
	 * Search an existing employee by its identifier
	 * 
	 * @param employeeId		employee identifier
	 * @return					employee
	 */
	public Employee searchEmployeeById(final Long employeeId);
	
	/**
	 * Removes an employee from DB.
	 * 
	 * @param employeeId		identifier of the employee to be removed.
	 */
	public boolean deleteEmployee(final Long employeeId);
	
	/**
	 * Search Departments for a particular organisation. 
	 * 
	 * @param organisationId	identifier of organisation
	 * @return					List of Departments for organisationId
	 */
	public List<Department> getDepartmentsForOrganisation(final Long organisationId);
	
	/**
	 * Returns Organisations.
	 */
	public List<Organisation> getOrganisations();

}
