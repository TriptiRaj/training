/**
 * 
 */
package com.emp.angularjs.business.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.emp.angularjs.business.entity.Department;

/**
 * @author Tripti
 *
 */
public interface DepartmentJPARepository extends CrudRepository<Department, Long> {

	public List<Department> findByOrganisationId(Long organisationId);
}
