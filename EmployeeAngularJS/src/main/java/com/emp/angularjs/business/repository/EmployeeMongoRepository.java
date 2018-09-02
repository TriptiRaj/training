/**
 * 
 */
package com.emp.angularjs.business.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emp.angularjs.business.entity.Employee;

/**
 * @author Tripti
 * 
 * Repository for Employee collection within Mongo DB.
 */
public interface EmployeeMongoRepository extends MongoRepository<Employee, String> {

	public Employee findByEmployeeId(Long employeeId);
	
	public void deleteByEmployeeId(Long employeeId);
	
	public List<Employee> findByOrganisationIdAndDepartmentId(Long organisationId, Long departmentId);
}
