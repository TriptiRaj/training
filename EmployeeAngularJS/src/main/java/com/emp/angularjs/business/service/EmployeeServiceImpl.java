/**
 * 
 */
package com.emp.angularjs.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emp.angularjs.business.entity.Department;
import com.emp.angularjs.business.entity.Employee;
import com.emp.angularjs.business.entity.Organisation;
import com.emp.angularjs.business.repository.DepartmentJPARepository;
import com.emp.angularjs.business.repository.EmployeeJPARepository;
import com.emp.angularjs.business.repository.OrganisationJPARepository;


/**
 * @author Tripti
 *
 */
@Service
@EnableTransactionManagement
public class EmployeeServiceImpl implements EmployeeService{

/*	@Autowired
	private EmployeeDataAccess empDataAccess;*/
	
/*	@Autowired
	private MongoDBDataAccess empDataAccess;*/
	
/*	@Autowired
	private EmployeeMongoRepository empMongoRepository;
	
	@Autowired
	private OrganisationMongoRepository orgMongoRepository;
	
	@Autowired
	private SequenceMongoDataAccessImpl sequenceMongoDataAccessImpl;*/
	
	@Autowired
	private EmployeeJPARepository empJpaRepository;
	
	@Autowired
	private DepartmentJPARepository deptJpaRepository;
	
	@Autowired
	private OrganisationJPARepository orgJpaRepository;
	
	@Override
	@Transactional
	public void addEmployee(final Employee employee) {
		empJpaRepository.save(employee);
	}

	@Override
	@Transactional
	public void updateEmplpoyee(final Employee employee) {
		empJpaRepository.save(employee);
	}

	@Override
	public List<Employee> searchEmployee(final Long organisationId,final Long departmentId) {
		List <Employee> employees = empJpaRepository.findByOrganisationIdAndDepartmentId(organisationId, departmentId);
		return employees;
	}

	@Override
	public Employee searchEmployeeById(final Long employeeId) {
		Employee employee = empJpaRepository.findByEmployeeId(employeeId);
		return employee;
	}

	@Override
	@Transactional
	public void deleteEmployee(final Long employeeId) {
		empJpaRepository.deleteByEmployeeId(employeeId);		
	}

	@Override
	public List<Department> getDepartmentsForOrganisation(final Long organisationId) {
		List<Department> departments = deptJpaRepository.findByOrganisationId(organisationId);
		return departments;
	}

	@Override
	public List<Organisation> getOrganisations() {
		Iterable<Organisation> organisations = orgJpaRepository.findAll();
		List <Organisation> organisationList = new ArrayList<>();
		for(Organisation organisation : organisations) {
			organisationList.add(organisation);
		}
		return organisationList;
	}
}
