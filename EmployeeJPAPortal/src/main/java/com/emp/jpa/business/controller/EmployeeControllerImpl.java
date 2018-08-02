/**
 * 
 */
package com.emp.jpa.business.controller;

import java.util.List;

import com.emp.jpa.business.entity.Department;
import com.emp.jpa.business.entity.Employee;
import com.emp.jpa.business.service.EmployeeService;
import com.emp.jpa.business.service.EmployeeServiceImpl;


/**
 * @author Tripti
 *
 */
public class EmployeeControllerImpl implements EmployeeController {

	/* (non-Javadoc)
	 * @see com.emp.business.controller.EmployeeController#addEmployee(com.emp.business.entity.Employee)
	 */
	@Override
	public void addEmployee(final Employee employee) {
		EmployeeService service = new EmployeeServiceImpl();
		service.addEmployee(employee);
	}

	/* (non-Javadoc)
	 * @see com.emp.business.controller.EmployeeController#updateEmplpoyee(com.emp.business.entity.Employee)
	 */
	@Override
	public void updateEmplpoyee(final Employee employee) {
		EmployeeService service = new EmployeeServiceImpl();
		service.updateEmplpoyee(employee);
	}

	/* (non-Javadoc)
	 * @see com.emp.business.controller.EmployeeController#searchEmployee(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Employee> searchEmployee(final Long organisationId, final Long departmentId) {
		EmployeeService service = new EmployeeServiceImpl();
		List <Employee> employees = service.searchEmployee(organisationId, departmentId);
		return employees;
	}

	/* (non-Javadoc)
	 * @see com.emp.business.controller.EmployeeController#searchEmployeeById(java.lang.Long)
	 */
	@Override
	public Employee searchEmployeeById(final Long employeeId) {
		EmployeeService service = new EmployeeServiceImpl();
		Employee employee = service.searchEmployeeById(employeeId);
		return employee;
	}

	/* (non-Javadoc)
	 * @see com.emp.business.controller.EmployeeController#deleteEmployee(java.lang.Long)
	 */
	@Override
	public void deleteEmployee(final Long employeeId) {
		EmployeeService service = new EmployeeServiceImpl();
		service.deleteEmployee(employeeId);		
	}

	/* (non-Javadoc)
	 * @see com.emp.business.controller.EmployeeController#getDepartmentsForOrganisation(java.lang.Long)
	 */
	@Override
	public List<Department> getDepartmentsForOrganisation(final Long organisationId) {
		EmployeeService service = new EmployeeServiceImpl();
		List <Department> departments = service.getDepartmentsForOrganisation(organisationId);
		return departments;
	}
}
