/**
 * 
 */
package com.emp.angularjs.business.dataaccess;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;

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
	private MongoDatabase db;
	
	/* (non-Javadoc)
	 * @see com.emp.angularjs.business.dataaccess.EmployeeDataAccess#addEmployee(com.emp.angularjs.business.entity.Employee)
	 */
	@Override
	public void addEmployee(Employee employee) {
		Long generatedValue = 0l;
		MongoCollection countersCollection = db.getCollection("counters");
		Document beforeUpdate = (Document)countersCollection.findOneAndUpdate(new BasicDBObject("seqType", "EMP_SEQ"), new BasicDBObject("$inc", new BasicDBObject("sequenceValue", 1)));
		generatedValue = beforeUpdate.getLong("sequenceValue") + 1 ;
		LOGGER.debug("The incr value is : " +  generatedValue);
		
		employee.setEmployeeId(generatedValue);
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
		
        LOGGER.debug(employee+" Updated into DB");
        LOGGER.info("Employee successfully updated.");
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
        LOGGER.debug("Employees fetched for Organinsation Id - "+organisationId+" and department Id - "+departmentId+" are - "+employeeList);
        LOGGER.info("Employees fetched succesfully.");
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
        LOGGER.debug("Employee fetched for Employee Id - "+employeeId+" is - "+employee);
        LOGGER.info("Employee fetched succesfully.");
		return employee;
	}

	/* (non-Javadoc)
	 * @see com.emp.angularjs.business.dataaccess.EmployeeDataAccess#deleteEmployee(java.lang.Long)
	 */
	@Override
	public boolean deleteEmployee(Long employeeId) {
		MongoCollection<Employee> collection = db.getCollection("employeeDetails", Employee.class);
		//filter
		BasicDBObject filter = new BasicDBObject();
		filter.append("employeeId", employeeId);
		//find
		DeleteResult result = collection.deleteOne(filter);
        LOGGER.debug("Employee with Employee Id - "+employeeId+" is deleted.");
        LOGGER.info("Employee deleted succesfully.");
		return result.wasAcknowledged();
	}

	/* (non-Javadoc)
	 * @see com.emp.angularjs.business.dataaccess.EmployeeDataAccess#getDepartmentsForOrganisation(java.lang.Long)
	 */
	@Override
	public List<Department> getDepartmentsForOrganisation(Long organisationId) {
		MongoCollection<Organisation> collection = db.getCollection("organisations", Organisation.class);
		//filter to apply
		BasicDBObject filter = new BasicDBObject();
		filter.append("organisationId", organisationId);
		
		//find
		Organisation organisation =  collection.find(filter,Organisation.class).first();
		List<Department> departmentList = organisation.getDepartments();
        LOGGER.debug("Departments fetched for organisation Id - "+organisationId+" are - "+departmentList);
        LOGGER.info("Departments for selected organisation fetched succesfully.");
		return departmentList;
	}

	/* (non-Javadoc)
	 * @see com.emp.angularjs.business.dataaccess.EmployeeDataAccess#getOrganisations()
	 */
	@Override
	public List<Organisation> getOrganisations() {
		MongoCollection<Organisation> collection = db.getCollection("organisations", Organisation.class);
		
		//find
		List<Organisation> organisationList =  collection.find().projection(
						Projections.include("organisationId","organisationName")).into(new ArrayList<Organisation>());
        LOGGER.debug("Organisations fetched are - "+organisationList);
        LOGGER.info("Organisations fetched succesfully.");
		return organisationList;
	}
}
