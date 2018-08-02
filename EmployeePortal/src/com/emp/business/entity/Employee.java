/**
 * 
 */
package com.emp.business.entity;

/**
 * @author Tripti
 * 
 * Represents an Employee
 */
public class Employee {

	private Long employeeId;
	
	private String employeeFullName;
	
	private Long organisationId;
	
	private Long departmentId;

	/**
	 * @return the employeeId
	 */
	public final Long getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public final void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the employeeFullName
	 */
	public final String getEmployeeFullName() {
		return employeeFullName;
	}

	/**
	 * @param employeeFullName the employeeFullName to set
	 */
	public final void setEmployeeFullName(String employeeFullName) {
		this.employeeFullName = employeeFullName;
	}

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
	
	@Override
	public String toString() {
		String result = null;
		if(this!=null) {
			result = "Employee Id - "+this.getEmployeeId()+
					"Employee Full Name - "+this.getEmployeeFullName()+
					"Department Id - "+this.getDepartmentId()+
					"Organisation Id - "+this.getOrganisationId();
			System.out.println(result);			
		}
		return result;
	}
}
