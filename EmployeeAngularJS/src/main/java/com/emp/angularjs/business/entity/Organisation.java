/**
 * 
 */
package com.emp.angularjs.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Tripti
 * 
 * Represents Organisation
 */

@Entity
@Table(name="SYS.ORGANISATION")
//@Document(collection = "organisations")
public class Organisation {
	
	@Id
	@Column(name="ORGANISATION_ID")
	private Long organisationId;
	
	@Column(name="ORGANISATION_NAME")
	private String organisationName;
	
/*	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Department> departments;*/

	/**
	 * @return the organisationId
	 */
	public Long getOrganisationId() {
		return organisationId;
	}

/*	*//**
	 * @return the departments
	 *//*
	public List<Department> getDepartments() {
		return departments;
	}

	*//**
	 * @param departments the departments to set
	 *//*
	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}*/

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
