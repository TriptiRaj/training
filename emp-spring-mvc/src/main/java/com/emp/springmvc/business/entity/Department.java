/**
 * 
 */
package com.emp.springmvc.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Tripti
 * 
 * Represents Department
 */

@Entity
@Table(name="SYS.DEPARTMENT")
public class Department {

	@Column(name="DEPARTMENT_ID")
	@Id
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS.DEPARTMENT_ID_SEQ")
	private Long departmentId;
	
	@Column(name="DEPARTMENT_NAME")
	private String departmentName;
	
	@Column(name="ORGANISATION_ID")
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
