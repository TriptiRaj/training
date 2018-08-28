/**
 * 
 */
package com.emp.angularjs.business.dataaccess;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.emp.angularjs.business.config.MongoConfig;
import com.emp.angularjs.business.entity.Department;
import com.emp.angularjs.business.entity.Employee;
import com.emp.angularjs.business.entity.Organisation;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

/**
 * @author Tripti
 * 
 * Data Access class for Mongo DB.
 */
public class MongoDBDataAccess implements EmployeeDataAccess {

	private static final Logger LOGGER = LogManager.getLogger(MongoDBDataAccess.class);
	
	@Autowired
	public static MongoDatabase db;
	
	public static void main(String[] args) {

/*		//Code to test add emp	
  		db = MongoConfig.getMongoDB();
		MongoCollection<Employee> collection = db.getCollection("employeeDetails", Employee.class);
		Employee employee = new Employee();
		employee.setDepartmentId(140l);
		employee.setOrganisationId(200l);
		employee.setEmployeeId(1l);
		employee.setEmployeeFullName("Simba King");
		collection.insertOne(employee);
        LOGGER.debug(employee+" Inserted into DB");
        LOGGER.info("Employee successfully added.");		
		*/
		
/*		//code to test update employee
  		db = MongoConfig.getMongoDB();
		MongoCollection<Employee> collection = db.getCollection("employeeDetails", Employee.class);
		Employee employee = new Employee();
		employee.setDepartmentId(111l);
		employee.setOrganisationId(222l);
		employee.setEmployeeId(1l);
		employee.setEmployeeFullName("Chaitu the prince");
		new MongoDBDataAccess().updateEmplpoyee(employee);*/

/*		//code to test search by orgId and deptId
  		db = MongoConfig.getMongoDB();
		MongoCollection<Employee> collection = db.getCollection("employeeDetails", Employee.class);
		Employee employee = new Employee();
		employee.setDepartmentId(111l);
		employee.setOrganisationId(222l);
		new MongoDBDataAccess().searchEmployee(employee.getOrganisationId(), employee.getDepartmentId());*/
		
/*		//code to test search by emp Id
  		db = MongoConfig.getMongoDB();
		MongoCollection<Employee> collection = db.getCollection("employeeDetails", Employee.class);
		Employee employee = new Employee();
		employee.setEmployeeId(1l);
		new MongoDBDataAccess().searchEmployeeById(employee.getEmployeeId());*/
		
		//code to test delete emp
  		db = MongoConfig.getMongoDB();
		MongoCollection<Employee> collection = db.getCollection("employeeDetails", Employee.class);
		Employee employee = new Employee();
		employee.setEmployeeId(1l);
		new MongoDBDataAccess().deleteEmployee(employee.getEmployeeId());
	}
	
	/* (non-Javadoc)
	 * @see com.emp.angularjs.business.dataaccess.EmployeeDataAccess#addEmployee(com.emp.angularjs.business.entity.Employee)
	 */
	@Override
	public void addEmployee(Employee employee) {
		MongoCollection<Employee> collection = db.getCollection("employeeDetails", Employee.class);
		collection.insertOne(employee);
        LOGGER.debug(employee+" Inserted into DB");
        LOGGER.info("Employee successfully added.");
	}

	/* (non-Javadoc)
	 * @see com.emp.angularjs.business.dataaccess.EmployeeDataAccess#updateEmplpoyee(com.emp.angularjs.business.entity.Employee)
	 */
	@Override
	public void updateEmplpoyee(Employee employee) {
		MongoCollection<Employee> collection = db.getCollection("employeeDetails", Employee.class);
		//filter
		BasicDBObject filter = new BasicDBObject();
		filter.append("employeeId", employee.getEmployeeId());
		
		//criteria
		BasicDBObject finalValues = new BasicDBObject();
		finalValues.append("employeeFullName", employee.getEmployeeFullName());
		finalValues.append("departmentId", employee.getDepartmentId());
		finalValues.append("organisationId", employee.getOrganisationId());
		
		//final update BSON
		BasicDBObject update = new BasicDBObject();
		update.append("$set", finalValues);
		collection.findOneAndUpdate(filter, update);
		
        LOGGER.debug(employee+" Inserted into DB");
        LOGGER.info("Employee successfully added.");

	}

	/* (non-Javadoc)
	 * @see com.emp.angularjs.business.dataaccess.EmployeeDataAccess#searchEmployee(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Employee> searchEmployee(Long organisationId, Long departmentId) {
		MongoCollection<Employee> collection = db.getCollection("employeeDetails", Employee.class);
		//filter
		BasicDBObject filter = new BasicDBObject();
		filter.append("organisationId", organisationId);
		filter.append("departmentId", departmentId);
		//find
		FindIterable<Employee> empIterable =  collection.find(filter, Employee.class);
		List<Employee> employeeList = new ArrayList<Employee>();
		for(Employee emp : empIterable) {
			employeeList.add(emp);
		}
		return employeeList;
	}

	/* (non-Javadoc)
	 * @see com.emp.angularjs.business.dataaccess.EmployeeDataAccess#searchEmployeeById(java.lang.Long)
	 */
	@Override
	public Employee searchEmployeeById(Long employeeId) {
		MongoCollection<Employee> collection = db.getCollection("employeeDetails", Employee.class);
		//filter
		BasicDBObject filter = new BasicDBObject();
		filter.append("employeeId", employeeId);
		//find
		FindIterable<Employee> empIterable =  collection.find(filter);
		Employee employee = empIterable.first();
		return employee;
	}

	/* (non-Javadoc)
	 * @see com.emp.angularjs.business.dataaccess.EmployeeDataAccess#deleteEmployee(java.lang.Long)
	 */
	@Override
	public void deleteEmployee(Long employeeId) {
		MongoCollection<Employee> collection = db.getCollection("employeeDetails", Employee.class);
		//filter
		BasicDBObject filter = new BasicDBObject();
		filter.append("employeeId", employeeId);
		//find
		DeleteResult result = collection.deleteOne(filter);
		//return result.wasAcknowledged();
	}

	/* (non-Javadoc)
	 * @see com.emp.angularjs.business.dataaccess.EmployeeDataAccess#getDepartmentsForOrganisation(java.lang.Long)
	 */
	@Override
	public List<Department> getDepartmentsForOrganisation(Long organisationId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.emp.angularjs.business.dataaccess.EmployeeDataAccess#getOrganisations()
	 */
	@Override
	public List<Organisation> getOrganisations() {
		// TODO Auto-generated method stub
		return null;
	}

}
