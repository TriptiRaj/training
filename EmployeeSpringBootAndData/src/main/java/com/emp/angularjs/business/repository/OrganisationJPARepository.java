/**
 * 
 */
package com.emp.angularjs.business.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emp.angularjs.business.entity.Organisation;

/**
 * @author Tripti
 *
 */
@Repository
public interface OrganisationJPARepository extends CrudRepository<Organisation, Long> {

}
