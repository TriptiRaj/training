/**
 * 
 */
package com.emp.jpa.business.service;

import java.util.List;

import com.emp.jpa.business.dataaccess.EmployeeDataAccess;
import com.emp.jpa.business.dataaccess.EmployeeDataAccessImpl;
import com.emp.jpa.business.entity.Department;
import com.emp.jpa.business.entity.Employee;



/**
 * @author Tripti
 *
 */
public class EmployeeServiceImpl implements EmployeeService{

	@Override
	public void addEmployee(final Employee employee) {
		EmployeeDataAccess dataAccess = new EmployeeDataAccessImpl();
		dataAccess.addEmployee(employee);
	}

	@Override
	public void updateEmplpoyee(final Employee employee) {
		EmployeeDataAccess dataAccess = new EmployeeDataAccessImpl();
		dataAccess.updateEmplpoyee(employee);
	}

	@Override
	public List<Employee> searchEmployee(final Long organisationId,final Long departmentId) {
		EmployeeDataAccess dataAccess = new EmployeeDataAccessImpl();
		List <Employee> employees = dataAccess.searchEmployee(organisationId, departmentId);
		return employees;
	}

	@Override
	public Employee searchEmployeeById(final Long employeeId) {
		EmployeeDataAccess dataAccess = new EmployeeDataAccessImpl();
		Employee employee = dataAccess.searchEmployeeById(employeeId);
		return employee;
	}

	@Override
	public void deleteEmployee(final Long employeeId) {
		EmployeeDataAccess dataAccess = new EmployeeDataAccessImpl();
		dataAccess.deleteEmployee(employeeId);		
	}

	@Override
	public List<Department> getDepartmentsForOrganisation(final Long organisationId) {
		EmployeeDataAccess dataAccess = new EmployeeDataAccessImpl();
		List <Department> departments = dataAccess.getDepartmentsForOrganisation(organisationId);
		return departments;
	}

}
