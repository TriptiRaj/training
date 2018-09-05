/**
 * 
 */
package com.emp.angularjs.business.entity;

/**
 * @author Tripti
 * 
 * Represents Department
 */

public class Department {

	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS.DEPARTMENT_ID_SEQ")
	private Long departmentId;
	
	private String departmentName;
	
	private Long organisationId;

	/**
	 * @return the organisationId
	 */
	public Long getOrganisationId() {
		return organisationId;
	}

	/**
	 * @param organisationId the organisationId to set
	 */
	public void setOrganisationId(final Long organisationId) {
		this.organisationId = organisationId;
	}

	/**
	 * @return the departmentId
	 */
	public Long getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(final Long departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(final String departmentName) {
		this.departmentName = departmentName;
	}
}
