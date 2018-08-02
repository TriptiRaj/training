/**
 * 
 */
package com.emp.business.service;

import java.util.List;

import com.emp.business.entity.Department;
import com.emp.business.entity.Employee;

/**
 * @author Tripti
 *
 */
public interface EmployeeService {
	
	public void addEmployee(final Employee employee);
	
	public void updateEmplpoyee(final Employee employee);
	
	public List<Employee> searchEmployee(final Long organisationId, final Long departmentId);

	public Employee searchEmployeeById(final Long employeeId);
	
	public void deleteEmployee(final Long employeeId);
	
	public List<Department> getDepartmentsForOrganisation(final Long organisationId);

}
