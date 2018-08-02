/**
 * 
 */
package com.emp.jpa.business.dataaccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.emp.jpa.business.entity.Department;
import com.emp.jpa.business.entity.Employee;


/**
 * Implementation class for EmployeeDataAccess.
 * 
 * @author Tripti
 *
 */
public class EmployeeDataAccessImpl implements EmployeeDataAccess {
	
	private static final Logger LOGGER = LogManager.getLogger(EmployeeDataAccessImpl.class);
	
	private static EntityManager entityManager = null;

	/*
	 * (non-Javadoc)
	 * @see com.emp.jpa.business.dataaccess.EmployeeDataAccess#addEmployee(com.emp.jpa.business.entity.Employee)
	 */
	@Override
	public void addEmployee(final Employee employee) {
		EntityManager entityMnager = getEntityManager();
		EntityTransaction transaction = entityMnager.getTransaction();
		transaction.begin();
		entityMnager.persist(employee);
		transaction.commit();
        LOGGER.debug(employee+" Inserted into DB");
        LOGGER.info("Employee successfully added.");
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.emp.jpa.business.dataaccess.EmployeeDataAccess#updateEmplpoyee(com.emp.jpa.business.entity.Employee)
	 */
	@Override
	public void updateEmplpoyee(final Employee employee) {		
		EntityManager entityMnager = getEntityManager();
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
	 * @see com.emp.jpa.business.dataaccess.EmployeeDataAccess#searchEmployee(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Employee> searchEmployee(final Long organisationId, final Long departmentId) {
		EntityManager entityMnager = getEntityManager();
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
	 * @see com.emp.jpa.business.dataaccess.EmployeeDataAccess#searchEmployeeById(java.lang.Long)
	 */
	@Override
	public Employee searchEmployeeById(final Long employeeId) {
		EntityManager entityMnager = getEntityManager();
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
	 * @see com.emp.jpa.business.dataaccess.EmployeeDataAccess#deleteEmployee(java.lang.Long)
	 */
	@Override
	public void deleteEmployee(final Long employeeId) {
		EntityManager entityMnager = getEntityManager();
		EntityTransaction transaction = entityMnager.getTransaction();
		transaction.begin();
		Employee employee = entityMnager.find(Employee.class, employeeId);
		entityMnager.remove(employee);
        LOGGER.debug("Employee with employee id - "+employeeId+" deleted");
        LOGGER.info("Employee deleted successfully.");
		transaction.commit();
	}

	/*
	 * (non-Javadoc)
	 * @see com.emp.jpa.business.dataaccess.EmployeeDataAccess#getDepartmentsForOrganisation(java.lang.Long)
	 */
	@Override
	public List<Department> getDepartmentsForOrganisation(final Long organisationId) {
		EntityManager entityMnager = getEntityManager();
		EntityTransaction transaction = entityMnager.getTransaction();
		transaction.begin();
		TypedQuery<Department> query = entityMnager.createQuery("Select A FROM Department A WHERE organisationId=:orgId", Department.class);
		query.setParameter("orgId", organisationId);
		List<Department> depList = query.getResultList();
		transaction.commit();
        LOGGER.debug("List of departments fetched - "+depList+" for organisation id - "+organisationId);
        LOGGER.info("Employee deleted successfully.");
		return depList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.emp.jpa.business.dataaccess.EmployeeDataAccess#getEntityManager()
	 */
	//TODO: Move this method to a separate Utility class 
	@Override
	public EntityManager getEntityManager() {
		if(entityManager==null) {
			final EntityManagerFactory entityManagFactory = 
					Persistence.createEntityManagerFactory("EmployeePersistentUnit");
			entityManager = entityManagFactory.createEntityManager();
	        LOGGER.info("Entity Manager succesfully created.");
		}
		return entityManager;
	}
}
