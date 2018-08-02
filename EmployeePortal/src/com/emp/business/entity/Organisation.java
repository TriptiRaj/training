/**
 * 
 */
package com.emp.business.entity;

import java.util.List;

/**
 * @author Tripti
 * 
 * Represents Organisation
 */
public class Organisation {
	private Long organisationId;
	
	private String organisationName;
	
	private List<Department> departments;

	/**
	 * @return the organisationId
	 */
	public final Long getOrganisationId() {
		return organisationId;
	}

	/**
	 * @return the departments
	 */
	public final List<Department> getDepartments() {
		return departments;
	}

	/**
	 * @param departments the departments to set
	 */
	public final void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	/**
	 * @param organisationId the organisationId to set
	 */
	public final void setOrganisationId(Long organisationId) {
		this.organisationId = organisationId;
	}

	/**
	 * @return the organisationName
	 */
	public final String getOrganisationName() {
		return organisationName;
	}

	/**
	 * @param organisationName the organisationName to set
	 */
	public final void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

}
