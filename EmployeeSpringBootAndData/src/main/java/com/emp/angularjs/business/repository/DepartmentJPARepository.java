/**
 * 
 */
package com.emp.angularjs.business.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emp.angularjs.business.entity.Department;

/**
 * @author Tripti
 *
 */
@Repository
public interface DepartmentJPARepository extends CrudRepository<Department, Long> {

	public List<Department> findByOrganisationId(Long organisationId);
}
