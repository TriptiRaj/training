/**
 * 
 */
package com.emp.business.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.emp.business.entity.Department;
import com.emp.business.entity.Employee;

/**
 * @author Tripti
 *
 */
public class EmployeeDataAccessImpl implements EmployeeDataAccess {
	
	private static Connection connection = null;
	
	private static Connection getJBDCConnection() {
		if(connection==null) {			
			synchronized (EmployeeDataAccessImpl.class) {
				if(connection==null) {
					String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
					String user = "SYSTEM";
					String passwd = "Shreyas_02";
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						connection = DriverManager.getConnection(jdbcURL, user, passwd);
					} catch (ClassNotFoundException classNotFoundException) {
						// TODO Add Logger statement
						classNotFoundException.printStackTrace();
					} catch (SQLException sqlException) {
						// TODO Add Logger statement
						sqlException.printStackTrace();
					}
				}
			}
		}
		return connection;
	}

	@Override
	public void addEmployee(final Employee employee) {
		getJBDCConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement("INSERT INTO SYS.EMPLOYEE (EMP_ID, EMP_FULL_NAME, ORGANISATION_ID, DEPARTMENT_ID)"
					+ "VALUES (SYS.EMPLOYEE_ID_SEQ.NEXTVAL, ?, ?, ?)");
			stmt.setString(1, employee.getEmployeeFullName());
			stmt.setLong(2, employee.getOrganisationId());
			stmt.setLong(3, employee.getDepartmentId());
			stmt.execute();
		} catch (SQLException exception) {
			// TODO Add Logger statement
			exception.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateEmplpoyee(final Employee employee) {
		
		getJBDCConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement("UPDATE SYS.EMPLOYEE SET DEPARTMENT_ID=?, ORGANISATION_ID=? WHERE EMP_ID=?");
			stmt.setLong(1, employee.getDepartmentId());
			stmt.setLong(2, employee.getOrganisationId());
			stmt.setLong(3, employee.getEmployeeId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Employee> searchEmployee(final Long organisationId, final Long departmentId) {
		getJBDCConnection();
		PreparedStatement stmt = null;
		ResultSet rs =null;
		List<Employee> employees = new ArrayList<Employee>();
		try {
			stmt = connection.prepareStatement("SELECT * FROM SYS.EMPLOYEE WHERE ORGANISATION_ID=? AND DEPARTMENT_ID=?");
			stmt.setLong(1, organisationId);
			stmt.setLong(2, departmentId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getLong(1));
				employee.setEmployeeFullName(rs.getString(2));
				employee.setOrganisationId(rs.getLong(3));
				employee.setDepartmentId(rs.getLong(4));	
				employees.add(employee);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}

		return employees;
	}
	
/*	public static void main(final String[] args) {
		EmployeeDataAccessImpl impl = new EmployeeDataAccessImpl();
		Employee emp = new Employee();
		emp.setEmployeeFullName("Chaitanya Jadhav");
		emp.setEmployeeId(100L);
		emp.setOrganisationId(1111l);
		emp.setDepartmentId(2222l);
		//impl.addEmployee(emp);
		//impl.updateEmplpoyee(emp);
		//impl.getDepartmentsForOrganisation(3232l);
		impl.searchEmployee(1111l, 2222l);
	}*/

	@Override
	public Employee searchEmployeeById(final Long employeeId) {
		getJBDCConnection();
		PreparedStatement stmt = null;
		ResultSet rs =null;
		Employee employee = null;
		try {
			stmt = connection.prepareStatement("SELECT * FROM SYS.EMPLOYEE WHERE EMP_ID=?");
			stmt.setLong(1, employeeId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				employee = new Employee();
				employee.setEmployeeId(employeeId);
				employee.setEmployeeFullName(rs.getString(2));
				employee.setOrganisationId(rs.getLong(3));
				employee.setDepartmentId(rs.getLong(4));			 				 
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return employee;
	}

	@Override
	public void deleteEmployee(final Long employeeId) {
		
		getJBDCConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement("DELETE FROM SYS.EMPLOYEE WHERE EMP_ID=?");
			stmt.setLong(1, employeeId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Department> getDepartmentsForOrganisation(final Long organisationId) {
		getJBDCConnection();
		PreparedStatement stmt = null;
		ResultSet rs =null;
		List<Department> departments = new ArrayList<Department>();
		try {
			stmt = connection.prepareStatement("SELECT DEPARTMENT_NAME FROM SYS.DEPARTMENT WHERE ORGANISATION_ID=?");
			stmt.setLong(1, organisationId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Department department = new Department();
				//department.setDepartmentId(rs.getLong(1));
				department.setDepartmentName(rs.getString(1));
				//department.setOrganisationId(rs.getLong(3));
				departments.add(department);			 				 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return departments;
	}
}
