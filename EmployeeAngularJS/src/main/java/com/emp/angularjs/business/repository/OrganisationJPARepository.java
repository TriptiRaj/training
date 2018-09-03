/**
 * 
 */
package com.emp.angularjs.business.repository;

import org.springframework.data.repository.CrudRepository;

import com.emp.angularjs.business.entity.Organisation;

/**
 * @author Tripti
 *
 */
public interface OrganisationJPARepository extends CrudRepository<Organisation, Long> {

}
