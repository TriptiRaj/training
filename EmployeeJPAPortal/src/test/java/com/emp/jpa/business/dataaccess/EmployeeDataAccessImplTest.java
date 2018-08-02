/**
 * 
 */
package com.emp.jpa.business.dataaccess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.emp.jpa.business.entity.Department;
import com.emp.jpa.business.entity.Employee;

/**
 * @author Tripti
 *
 */
class EmployeeDataAccessImplTest {

	private static EmployeeDataAccess dataAccess = null;
	
	private static EntityManager entityManager = null;
	
	@BeforeAll
	static void setup() {
		dataAccess = new EmployeeDataAccessImpl();
		entityManager = dataAccess.getEntityManager();
	}
	
	/**
	 * Test method for {@link com.emp.jpa.business.dataaccess.EmployeeDataAccessImpl#addEmployee(com.emp.jpa.business.entity.Employee)}.
	 */
	@Test
	void testAddEmployee() {
		Employee employee = new Employee();
		employee.setEmployeeFullName("TestCase FullName");
		employee.setDepartmentId(11l);
		employee.setOrganisationId(121l);
		List<Employee> empListBeforeAdd = searchEmpByAllDetails(employee);
		int listSizeBefore = empListBeforeAdd.size();
		
		dataAccess.addEmployee(employee);
		List<Employee> empListAfterAdd = searchEmpByAllDetails(employee);
		int listSizeAfter = empListAfterAdd.size();
		
		assertEquals(listSizeAfter, listSizeBefore+1, "Employee not added");
	
	}
	
	/**
	 * Helper method for testAddEmployee().
	 * 
	 * @param employee
	 * @return
	 */
	private List<Employee> searchEmpByAllDetails(Employee employee) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		TypedQuery<Employee> query = entityManager.createQuery("Select A FROM Employee A WHERE employeeFullName =:empFullName and organisationId=:orgId AND departmentId =:deptId", Employee.class);
		query.setParameter("empFullName", employee.getEmployeeFullName());
		query.setParameter("orgId", employee.getOrganisationId());
		query.setParameter("deptId", employee.getDepartmentId());
		List<Employee> empList = query.getResultList();
		transaction.commit();
		return empList;
	}

	/**
	 * Helper method for some of the test methods.
	 * @return
	 */
	private Employee searchTopEmplForTesting() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		TypedQuery<Employee> query = entityManager.createQuery("Select A FROM Employee A WHERE ROWNUM = 1", Employee.class);
		Employee employee = query.getResultList().get(0);
		transaction.commit();
		return employee;
	}

	/**
	 * Test method for {@link com.emp.jpa.business.dataaccess.EmployeeDataAccessImpl#updateEmplpoyee(java.lang.Long)}.
	 */
	@Test
	void testUpdateEmplpoyee() {
		Employee employeeBeforeUpdate = searchTopEmplForTesting();
		if(employeeBeforeUpdate!=null) {
			Long empId = employeeBeforeUpdate.getEmployeeId();
			String fullName = employeeBeforeUpdate.getEmployeeFullName();
			Long orgId = employeeBeforeUpdate.getOrganisationId();
			Long deptId = employeeBeforeUpdate.getDepartmentId();

			// change values in employee object
			String updatedFullName = "JPA Rocks";
			Long updatedOrgId = 5252L;
			Long updatedDeptId = 9876L;
			
			assertNotEquals(updatedFullName, fullName, "Employee Full Name should not be same");
			assertNotEquals(updatedDeptId, deptId, "Employee Department Id should not be same");
			assertNotEquals(updatedOrgId, orgId, "Employee Organisation Id should not be same");
			
			Employee empUpdatedValues = new Employee();
			empUpdatedValues.setEmployeeId(empId);
			empUpdatedValues.setEmployeeFullName(updatedFullName);
			empUpdatedValues.setDepartmentId(updatedDeptId);
			empUpdatedValues.setOrganisationId(updatedOrgId);
			
			// call update
			dataAccess.updateEmplpoyee(empUpdatedValues);
			
			Employee empAfterUpdate = dataAccess.searchEmployeeById(empId);
			
			assertEquals(empId, empAfterUpdate.getEmployeeId(), "Employee Id should be same");
			assertEquals(updatedFullName, empAfterUpdate.getEmployeeFullName(), "Employee Full Name should be same");
			assertEquals(updatedDeptId.intValue(), empAfterUpdate.getDepartmentId().intValue(), "Employee Department Id should be same");
			assertEquals(updatedOrgId.intValue(), empAfterUpdate.getOrganisationId().intValue(), "Employee Organisation Id should be same");

			employeeBeforeUpdate.setEmployeeFullName(fullName);
			employeeBeforeUpdate.setOrganisationId(orgId);
			employeeBeforeUpdate.setDepartmentId(deptId);
			
			dataAccess.updateEmplpoyee(employeeBeforeUpdate);
		}
	}

	/**
	 * Test method for {@link com.emp.jpa.business.dataaccess.EmployeeDataAccessImpl#searchEmployee(java.lang.Long, java.lang.Long)}.
	 */
	@Test
	void testSearchEmployee() {
		Employee employeeBeforeSearch = searchTopEmplForTesting();
		if(employeeBeforeSearch!=null) {
			Long empId = employeeBeforeSearch.getEmployeeId();
			String fullName = employeeBeforeSearch.getEmployeeFullName();
			Long orgId = employeeBeforeSearch.getOrganisationId();
			Long deptId = employeeBeforeSearch.getDepartmentId();
			
			Employee empAfterSearch = dataAccess.searchEmployee(orgId, deptId).get(0);
			if(empAfterSearch!=null) {
				assertEquals(empId, empAfterSearch.getEmployeeId(), "Employee Id should be same");
				assertEquals(fullName, empAfterSearch.getEmployeeFullName(), "Employee Full Name should be same");
				assertEquals(deptId.intValue(), empAfterSearch.getDepartmentId().intValue(), "Employee Department Id should be same");
				assertEquals(orgId.intValue(), empAfterSearch.getOrganisationId().intValue(), "Employee Organisation Id should be same");
			}
		}
	}

	/**
	 * Test method for {@link com.emp.jpa.business.dataaccess.EmployeeDataAccessImpl#searchEmployeeById(java.lang.Long)}.
	 */
	@Test
	void testSearchEmployeeById() {
		Employee employeeBeforeUpdate = searchTopEmplForTesting();
		if(employeeBeforeUpdate!=null) {
			Long empId = employeeBeforeUpdate.getEmployeeId();
			String fullName = employeeBeforeUpdate.getEmployeeFullName();
			Long orgId = employeeBeforeUpdate.getOrganisationId();
			Long deptId = employeeBeforeUpdate.getDepartmentId();

			// call search by id
			Employee empAfterSearchById = dataAccess.searchEmployeeById(empId);
			
			assertEquals(empId, empAfterSearchById.getEmployeeId(), "Employee Id should be same");
			assertEquals(fullName, empAfterSearchById.getEmployeeFullName(), "Employee Full Name should be same");
			assertEquals(deptId.intValue(), empAfterSearchById.getDepartmentId().intValue(), "Employee Department Id should be same");
			assertEquals(orgId.intValue(), empAfterSearchById.getOrganisationId().intValue(), "Employee Organisation Id should be same");
		}
	}

	/**
	 * Test method for {@link com.emp.jpa.business.dataaccess.EmployeeDataAccessImpl#deleteEmployee(java.lang.Long)}.
	 */
	@Test
	void testDeleteEmployee() {
		Employee employee = searchTopEmplForTesting();		
		assertNotNull(employee+"employee object shouldn't be null.");
		
		// delete fetched employee
		dataAccess.deleteEmployee(employee.getEmployeeId());
		
		// fetching the same employee again
		Employee employeeAfterDelete = dataAccess.searchEmployeeById(employee.getEmployeeId());
		assertNull(employeeAfterDelete, "Employee is not deleted.");		
	}

	/**
	 * Test method for {@link com.emp.jpa.business.dataaccess.EmployeeDataAccessImpl#getDepartmentsForOrganisation(java.lang.Long)}.
	 */
	@Test
	void testGetDepartmentsForOrganisation() {
		
		Long orgId = 34l;
		String deptName = "some fancy name";
		//Long deptId = 55l; 
		Long deptId = insertIntoDepartmentForTesting(orgId, deptName);
		Department dep = dataAccess.getDepartmentsForOrganisation(orgId).get(0);
		assertEquals(orgId, dep.getOrganisationId(), "organisation Id must match");
		assertEquals(deptName, dep.getDepartmentName(), "department name must match");
		assertEquals(deptId, dep.getDepartmentId(), "department id must match");
		
		//delete the inserted record
		deleteDepartment(dep);
	}
	
	/**
	 * Helper method
	 */
	private void deleteDepartment(final Department department) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(department);
		transaction.commit();
	}
	/**
	 * Helper method for testGetDepartmentsForOrganisation().
	 * 
	 * @param organisationId
	 * @param deptName
	 * @param departmentId
	 */
	private Long insertIntoDepartmentForTesting(Long organisationId, String deptName) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Query query = entityManager.createNativeQuery("SELECT SYS.DEPARTMENT_ID_SEQ.NEXTVAL FROM DUAL");
		Object deptId = query.getSingleResult();
		
		Department department = new Department();
		department.setDepartmentId(Long.valueOf(deptId.toString()));
		department.setDepartmentName(deptName);
		department.setOrganisationId(organisationId);
		entityManager.persist(department);
		transaction.commit();
		return Long.valueOf(deptId.toString());
	}
}
