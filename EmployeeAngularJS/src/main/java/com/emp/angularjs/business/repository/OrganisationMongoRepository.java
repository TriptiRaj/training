/**
 * 
 */
package com.emp.angularjs.business.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emp.angularjs.business.entity.Organisation;

/**
 * @author Tripti
 *
 */
public interface OrganisationMongoRepository extends MongoRepository<Organisation, String> {

	public Organisation findByOrganisationId(Long organisationId);
}
