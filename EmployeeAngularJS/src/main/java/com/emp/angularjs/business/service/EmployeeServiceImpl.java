/**
 * 
 */
package com.emp.angularjs.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.angularjs.business.dataaccess.MongoDBDataAccess;
import com.emp.angularjs.business.entity.Department;
import com.emp.angularjs.business.entity.Employee;
import com.emp.angularjs.business.entity.Organisation;


/**
 * @author Tripti
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{

/*	@Autowired
	private EmployeeDataAccess empDataAccess;*/
	
	@Autowired
	private MongoDBDataAccess empDataAccess;
	
	@Override
	public void addEmployee(final Employee employee) {
		empDataAccess.addEmployee(employee);
	}

	@Override
	public void updateEmplpoyee(final Employee employee) {
		empDataAccess.updateEmplpoyee(employee);
	}

	@Override
	public List<Employee> searchEmployee(final Long organisationId,final Long departmentId) {
		List <Employee> employees = empDataAccess.searchEmployee(organisationId, departmentId);
		return employees;
	}

	@Override
	public Employee searchEmployeeById(final Long employeeId) {
		Employee employee = empDataAccess.searchEmployeeById(employeeId);
		return employee;
	}

	@Override
	public void deleteEmployee(final Long employeeId) {
		empDataAccess.deleteEmployee(employeeId);		
	}

	@Override
	public List<Department> getDepartmentsForOrganisation(final Long organisationId) {
		List <Department> departments = empDataAccess.getDepartmentsForOrganisation(organisationId);
		return departments;
	}

	@Override
	public List<Organisation> getOrganisations() {
		List <Organisation> organisations = empDataAccess.getOrganisations();
		return organisations;
	}
}
