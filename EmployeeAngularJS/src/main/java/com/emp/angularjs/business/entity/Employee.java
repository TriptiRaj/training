/**
 * 
 */
package com.emp.angularjs.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Tripti
 * 
 * Represents an Employee
 */

@Table(name="SYS.EMPLOYEE")
@Entity
public class Employee {

	@Id
	@Column(name="EMP_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS.EMPLOYEE_ID_SEQ")
	private Long employeeId;
	
	@Column(name = "EMP_FULL_NAME")
	private String employeeFullName;
	
	@Column(name = "ORGANISATION_ID")
	private Long organisationId;
	
	@Column(name="DEPARTMENT_ID")
	private Long departmentId;

	/**
	 * @return the employeeId
	 */
	public Long getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the employeeFullName
	 */
	public String getEmployeeFullName() {
		return employeeFullName;
	}

	/**
	 * @param employeeFullName the employeeFullName to set
	 */
	public void setEmployeeFullName(String employeeFullName) {
		this.employeeFullName = employeeFullName;
	}

	/**
	 * @return the organisationId
	 */
	public Long getOrganisationId() {
		return organisationId;
	}

	/**
	 * @param organisationId the organisationId to set
	 */
	public void setOrganisationId(Long organisationId) {
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
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}	
	
	@Override
	public String toString() {
		String result = null;
		if(this!=null) {
			result = "Employee Id - "+this.getEmployeeId()+
					", Employee Full Name - "+this.getEmployeeFullName()+
					", Department Id - "+this.getDepartmentId()+
					", Organisation Id - "+this.getOrganisationId();
			System.out.println(result);			
		}
		return result;
	}
}
