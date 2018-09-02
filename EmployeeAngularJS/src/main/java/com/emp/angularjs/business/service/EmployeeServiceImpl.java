/**
 * 
 */
package com.emp.angularjs.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.angularjs.business.dataaccess.SequenceMongoDataAccessImpl;
import com.emp.angularjs.business.entity.Department;
import com.emp.angularjs.business.entity.Employee;
import com.emp.angularjs.business.entity.Organisation;
import com.emp.angularjs.business.repository.EmployeeMongoRepository;
import com.emp.angularjs.business.repository.OrganisationMongoRepository;


/**
 * @author Tripti
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{

/*	@Autowired
	private EmployeeDataAccess empDataAccess;*/
	
/*	@Autowired
	private MongoDBDataAccess empDataAccess;*/
	
	@Autowired
	private EmployeeMongoRepository empMongoRepository;
	
	@Autowired
	private OrganisationMongoRepository orgMongoRepository;
	
	@Autowired
	private SequenceMongoDataAccessImpl sequenceMongoDataAccessImpl;
	
	@Override
	public void addEmployee(final Employee employee) {
		employee.setEmployeeId(sequenceMongoDataAccessImpl.generateNextValue("EMP_SEQ"));
		empMongoRepository.save(employee);
	}

	@Override
	public void updateEmplpoyee(final Employee employee) {
		empMongoRepository.save(employee);
	}

	@Override
	public List<Employee> searchEmployee(final Long organisationId,final Long departmentId) {
		List <Employee> employees = empMongoRepository.findByOrganisationIdAndDepartmentId(organisationId, departmentId);
		return employees;
	}

	@Override
	public Employee searchEmployeeById(final Long employeeId) {
		Employee employee = empMongoRepository.findByEmployeeId(employeeId);
		return employee;
	}

	@Override
	public void deleteEmployee(final Long employeeId) {
		empMongoRepository.deleteByEmployeeId(employeeId);		
	}

	@Override
	public List<Department> getDepartmentsForOrganisation(final Long organisationId) {
		Organisation currentOrganisation = orgMongoRepository.findByOrganisationId(organisationId);
		return currentOrganisation.getDepartments();
	}

	@Override
	public List<Organisation> getOrganisations() {
		List <Organisation> organisations = orgMongoRepository.findAll();
		return organisations;
	}
}
