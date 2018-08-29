/**
 * 
 */
package com.emp.angularjs.business.dataaccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emp.angularjs.business.entity.Department;
import com.emp.angularjs.business.entity.Employee;
import com.emp.angularjs.business.entity.Organisation;


/**
 * Implementation class for EmployeeDataAccess.
 * 
 * @author Tripti
 *
 */
@Component
public class EmployeeDataAccessImpl implements EmployeeDataAccess {
	
	private static final Logger LOGGER = LogManager.getLogger(EmployeeDataAccessImpl.class);
	
	@Autowired
	private EntityManager entityMnager;

	/*
	 * (non-Javadoc)
	 * @see com.emp.angularjs.business.dataaccess.EmployeeDataAccess#addEmployee(com.emp.angularjs.business.entity.Employee)
	 */
	@Override
	public void addEmployee(final Employee employee) {
		EntityTransaction transaction = entityMnager.getTransaction();
		transaction.begin();
		entityMnager.persist(employee);
		transaction.commit();
        LOGGER.debug(employee+" Inserted into DB");
        LOGGER.info("Employee successfully added.");
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.emp.angularjs.business.dataaccess.EmployeeDataAccess#updateEmplpoyee(com.emp.angularjs.business.entity.Employee)
	 */
	@Override
	public void updateEmplpoyee(final Employee employee) {		
		EntityTransaction transaction = entityMnager.getTransaction();
		transaction.begin();
		Employee emp = entityMnager.find(Employee.class, employee.getEmployeeId());
		emp.setEmployeeFullName(employee.getEmployeeFullName());
		emp.setDepartmentId(employee.getDepartmentId());
		emp.setOrganisationId(employee.getOrganisationId());
		entityMnager.merge(emp);
		transaction.commit();
        LOGGER.debug(employee+" Updated into DB");
        LOGGER.info("Employee successfully updated.");
	}

	/*
	 * (non-Javadoc)
	 * @see com.emp.angularjs.business.dataaccess.EmployeeDataAccess#searchEmployee(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Employee> searchEmployee(final Long organisationId, final Long departmentId) {
		EntityTransaction transaction = entityMnager.getTransaction();
		transaction.begin();
		TypedQuery<Employee> query = entityMnager.createQuery("Select A FROM Employee A WHERE organisationId=:orgId AND departmentId =:deptId", Employee.class);
		query.setParameter("orgId", organisationId);
		query.setParameter("deptId", departmentId);
		List<Employee> empList = query.getResultList();
		transaction.commit();
        LOGGER.debug(empList+" Searched employees list.");
        LOGGER.info("Employees search successfully executed.");
		return empList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.emp.angularjs.business.dataaccess.EmployeeDataAccess#searchEmployeeById(java.lang.Long)
	 */
	@Override
	public Employee searchEmployeeById(final Long employeeId) {
		EntityTransaction transaction = entityMnager.getTransaction();
		transaction.begin();
		Employee employee = entityMnager.find(Employee.class, employeeId);
		transaction.commit();
        LOGGER.debug(employee+" Searched employee.");
        LOGGER.info("Employee search successfully executed.");
		return employee;
	}

	/*
	 * (non-Javadoc)
	 * @see com.emp.angularjs.business.dataaccess.EmployeeDataAccess#deleteEmployee(java.lang.Long)
	 */
	@Override
	public boolean deleteEmployee(final Long employeeId) {
		EntityTransaction transaction = entityMnager.getTransaction();
		transaction.begin();
		Employee employee = entityMnager.find(Employee.class, employeeId);
		entityMnager.remove(employee);
        LOGGER.debug("Employee with employee id - "+employeeId+" deleted");
        LOGGER.info("Employee deleted successfully.");
		transaction.commit();
		// TODO: change this
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.emp.angularjs.business.dataaccess.EmployeeDataAccess#getDepartmentsForOrganisation(java.lang.Long)
	 */
	@Override
	public List<Department> getDepartmentsForOrganisation(final Long organisationId) {
		EntityTransaction transaction = entityMnager.getTransaction();
		transaction.begin();
		TypedQuery<Department> query = entityMnager.createQuery("Select A FROM Department A WHERE organisationId=:orgId", Department.class);
		query.setParameter("orgId", organisationId);
		List<Department> depList = query.getResultList();
		transaction.commit();
        LOGGER.debug("List of departments fetched - "+depList+" for organisation id - "+organisationId);
        LOGGER.info("List of departments fetched successfully.");
		return depList;
	}

	@Override
	public List<Organisation> getOrganisations() {
		EntityTransaction transaction = entityMnager.getTransaction();
		transaction.begin();
		TypedQuery<Organisation> query = entityMnager.createQuery("Select A FROM Organisation A", Organisation.class);
		List<Organisation> organisationList = query.getResultList();
		transaction.commit();
        LOGGER.debug("List of Organisation fetched - "+organisationList);
        LOGGER.info("List of Organisation fetched successfully.");
		return organisationList;
	}
}
