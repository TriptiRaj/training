/**
 * 
 */
package com.emp.angularjs.business.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emp.angularjs.business.entity.Employee;

/**
 * @author Tripti
 *
 */
@Repository
public interface EmployeeJPARepository extends CrudRepository<Employee, Long> {

	public Employee findByEmployeeId(Long employeeId);
	
	public void deleteByEmployeeId(Long employeeId);
	
	public List<Employee> findByOrganisationIdAndDepartmentId(Long organisationId, Long departmentId);
}
