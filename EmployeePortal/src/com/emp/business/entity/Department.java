/**
 * 
 */
package com.emp.business.entity;

/**
 * @author Tripti
 * 
 * Represents Department
 */
public class Department {

	private Long departmentId;
	
	private String departmentName;
	
	private Long organisationId;

	/**
	 * @return the organisationId
	 */
	public final Long getOrganisationId() {
		return organisationId;
	}

	/**
	 * @param organisationId the organisationId to set
	 */
	public final void setOrganisationId(final Long organisationId) {
		this.organisationId = organisationId;
	}

	/**
	 * @return the departmentId
	 */
	public final Long getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public final void setDepartmentId(final Long departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the departmentName
	 */
	public final String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public final void setDepartmentName(final String departmentName) {
		this.departmentName = departmentName;
	}
}
