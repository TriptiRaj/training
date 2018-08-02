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

import com.emp.jpa.business.entity.Department;
import com.emp.jpa.business.entity.Employee;


/**
 * @author Tripti
 *
 */
public class EmployeeDataAccessImpl implements EmployeeDataAccess {
	
	private static EntityManager entityManager = null;

	@Override
	public void addEmployee(final Employee employee) {
		EntityManager entityMnager = getEntityManager();
		EntityTransaction transaction = entityMnager.getTransaction();
		transaction.begin();
		entityMnager.persist(employee);
		transaction.commit();
	}

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
	}

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
		return empList;
	}
	
	@Override
	public Employee searchEmployeeById(final Long employeeId) {
		EntityManager entityMnager = getEntityManager();
		EntityTransaction transaction = entityMnager.getTransaction();
		transaction.begin();
		Employee employee = entityMnager.find(Employee.class, employeeId);
		transaction.commit();
		return employee;
	}

	@Override
	public void deleteEmployee(final Long employeeId) {
		EntityManager entityMnager = getEntityManager();
		EntityTransaction transaction = entityMnager.getTransaction();
		transaction.begin();
		Employee employee = entityMnager.find(Employee.class, employeeId);
		entityMnager.remove(employee);
		transaction.commit();
	}

	@Override
	public List<Department> getDepartmentsForOrganisation(final Long organisationId) {
		EntityManager entityMnager = getEntityManager();
		EntityTransaction transaction = entityMnager.getTransaction();
		transaction.begin();
		TypedQuery<Department> query = entityMnager.createQuery("Select A FROM Department A WHERE organisationId=:orgId", Department.class);
		query.setParameter("orgId", organisationId);
		List<Department> depList = query.getResultList();
		transaction.commit();
		return depList;
	}
	
	@Override
	public EntityManager getEntityManager() {
		if(entityManager==null) {
			final EntityManagerFactory entityManagFactory = 
					Persistence.createEntityManagerFactory("EmployeePersistentUnit");
			entityManager = entityManagFactory.createEntityManager();
		}
		return entityManager;
	}
}
