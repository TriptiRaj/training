/**
 * 
 */
package com.emp.angularjs.business.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Tripti
 * 
 * Represents Organisation
 */

@Document(collection = "organisations")
public class Organisation {
	
	//@Id
	private Long organisationId;
	
	private String organisationName;
	
	private List<Department> departments;

	/**
	 * @return the organisationId
	 */
	public Long getOrganisationId() {
		return organisationId;
	}

	/**
	 * @return the departments
	 */
	public List<Department> getDepartments() {
		return departments;
	}

	/**
	 * @param departments the departments to set
	 */
	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	/**
	 * @param organisationId the organisationId to set
	 */
	public void setOrganisationId(Long organisationId) {
		this.organisationId = organisationId;
	}

	/**
	 * @return the organisationName
	 */
	public String getOrganisationName() {
		return organisationName;
	}

	/**
	 * @param organisationName the organisationName to set
	 */
	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}
}
