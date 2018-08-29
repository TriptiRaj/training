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
import com.mongodb.client.model.Projections;
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
		
/*		//code to test delete emp
  		db = MongoConfig.getMongoDB();
		MongoCollection<Employee> collection = db.getCollection("employeeDetails", Employee.class);
		Employee employee = new Employee();
		employee.setEmployeeId(1l);
		new MongoDBDataAccess().deleteEmployee(employee.getEmployeeId());*/
		
		//code to test getOrganisations
  		db = MongoConfig.getMongoDB();
		MongoCollection<Employee> collection = db.getCollection("organisations", Employee.class);
		new MongoDBDataAccess().getOrganisations();
		
		//code to test search by orgId and deptId
/*  		db = MongoConfig.getMongoDB();
		MongoCollection<Employee> collection = db.getCollection("organisations", Employee.class);
		Employee employee = new Employee();
		employee.setOrganisationId(2l);
		new MongoDBDataAccess().getDepartmentsForOrganisation(employee.getOrganisationId());
		*/
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
		MongoCollection<Organisation> collection = db.getCollection("organisations", Organisation.class);
		//filter
		BasicDBObject filter = new BasicDBObject();
		filter.append("organisationId", organisationId);
		
		BasicDBObject projection = new BasicDBObject();
		projection.put("departments", 1);
		
		//find
		//FindIterable<Department> departmentIterable =  collection.find(filter,Department.class).projection(Projections.include("departments")).projection(Projections.exclude("_id"));
		Organisation organisation =  collection.find(filter,Organisation.class).first();
		List<Department> departmentList = organisation.getDepartments();

		return departmentList;
	}

	/* (non-Javadoc)
	 * @see com.emp.angularjs.business.dataaccess.EmployeeDataAccess#getOrganisations()
	 */
	@Override
	public List<Organisation> getOrganisations() {
		MongoCollection<Organisation> collection = db.getCollection("organisations", Organisation.class);

		//find
		FindIterable<Organisation> orgIterable =  collection.find().projection(Projections.include("organisationId","organisationName"));//(filter, Organisation.class);
		//FindIterable<Organisation> orgIterable =  collection.find();
		List<Organisation> organisationList = new ArrayList<Organisation>();
		
/*        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
        	organisationList.add(readValue(reader, decoderContext));
        }*/
        
		for(Organisation org : orgIterable) {
			organisationList.add(org);
		}
		return organisationList;
	}

}
