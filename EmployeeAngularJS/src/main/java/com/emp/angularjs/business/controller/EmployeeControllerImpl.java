/**
 * 
 */
package com.emp.angularjs.business.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.emp.angularjs.business.entity.Department;
import com.emp.angularjs.business.entity.Employee;
import com.emp.angularjs.business.entity.Organisation;
import com.emp.angularjs.business.service.EmployeeService;


/**
 * Implementation class for EmployeeController
 * 
 * @author Tripti
 */
@RestController
public class EmployeeControllerImpl implements EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayHomePage() {
		return "employeeAngularJS";
	}
	
	@RequestMapping("displayAddEmpPage")
	public ResponseEntity<List<Organisation>> displayAddEmployeePage() {
		List <Organisation>  organisationList = empService.getOrganisations();
		ResponseEntity<List<Organisation>> responseEntity = new ResponseEntity<List<Organisation>>(organisationList, HttpStatus.OK);
/*		List <Organisation>  organisationList = empService.getOrganisations();
		ModelAndView modelAndView = new ModelAndView("addEmployee");
		modelAndView.addObject("organisationList", organisationList);
		modelAndView.addObject("employee", new Employee());*/
		return responseEntity;
	}
	
	@RequestMapping("displaySearchEmpPage")
	public ModelAndView displaySearchEmployeePage() {
		List <Organisation>  organisationList = empService.getOrganisations();
		ModelAndView modelAndView = new ModelAndView("searchEmployee");
		modelAndView.addObject("organisationList", organisationList);
		modelAndView.addObject("employee", new Employee());
		return modelAndView;
	}
	
	@RequestMapping("displayUpdateEmpPage")
	public ModelAndView displayUpdateEmployeePage() {
		ModelAndView modelAndView = new ModelAndView("updateEmployee");
		modelAndView.addObject("employee", new Employee());
		return modelAndView;
	}
	
	@RequestMapping("displayDeleteEmpPage")
	public String displayDeleteEmployeePage() {
		return "deleteEmployee";
	}
	/* (non-Javadoc)
	 * @see com.emp.business.controller.EmployeeController#addEmployee(com.emp.business.entity.Employee)
	 */
	@Override
	@RequestMapping(value="addEmployee", method = RequestMethod.POST)
	public ResponseEntity<String> addEmployee(RequestEntity<Employee>  requestEntity) {
		Employee employee = new Employee();
		employee.setEmployeeFullName(requestEntity.getBody().getEmployeeFullName());
		employee.setOrganisationId(requestEntity.getBody().getOrganisationId());
		employee.setDepartmentId(requestEntity.getBody().getDepartmentId());
		empService.addEmployee(employee);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>("", HttpStatus.OK);			
		return responseEntity;
	}

	/* (non-Javadoc)
	 * @see com.emp.business.controller.EmployeeController#updateEmplpoyee(com.emp.business.entity.Employee)
	 */
	@Override
	@RequestMapping(value="updateEmployeeDetails", method=RequestMethod.POST)
	public void updateEmplpoyee(RequestEntity<Employee> requestEntity) {
		empService.updateEmplpoyee(requestEntity.getBody());
	}

	/* (non-Javadoc)
	 * @see com.emp.business.controller.EmployeeController#searchEmployee(java.lang.Long, java.lang.Long)
	 */
	@Override
	@RequestMapping(value="searchByOrgAndDept", method = RequestMethod.POST)
	public ResponseEntity<List<Employee>> searchEmployee(RequestEntity<Employee> requestEntity) {
		Employee employee = requestEntity.getBody();
		List <Employee> employeeList = empService.searchEmployee(employee.getOrganisationId(), employee.getDepartmentId());
		ResponseEntity<List<Employee>> responseEntity = new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
		return responseEntity;
	}

	/* (non-Javadoc)
	 * @see com.emp.business.controller.EmployeeController#searchEmployeeById(java.lang.Long)
	 */
	@Override
	@RequestMapping(value="searchByEmpId", method = RequestMethod.POST)
	public ResponseEntity<Employee> searchEmployeeById(RequestEntity<Long> requestEntity) {
		Employee employee = empService.searchEmployeeById(requestEntity.getBody());
		if(employee == null) {
			employee = new Employee();
		}
		ResponseEntity< Employee> responseEntity = new ResponseEntity<Employee>(employee,HttpStatus.OK);
		return responseEntity;
	}
	
	@RequestMapping(value="searchEmpToUpdate", method = RequestMethod.POST)
	public ModelAndView searchEmpToUpdate(@ModelAttribute("employee") Employee employee) {
		employee = empService.searchEmployeeById(employee.getEmployeeId());
		if(employee == null) {
			employee = new Employee();
		}
		List <Organisation>  organisationList = empService.getOrganisations();
		ModelAndView modelAndView = new ModelAndView("updateEmployee");
		modelAndView.addObject("employee", employee);
		modelAndView.addObject("organisationList", organisationList);
		return modelAndView;
	}

	/* (non-Javadoc)
	 * @see com.emp.business.controller.EmployeeController#deleteEmployee(java.lang.Long)
	 */
	@Override
	@RequestMapping(value="deleteEmployee", method = RequestMethod.POST)
	public void deleteEmployee(RequestEntity<Long> requestEntity) {
		empService.deleteEmployee(requestEntity.getBody());
	}

	/* (non-Javadoc)
	 * @see com.emp.business.controller.EmployeeController#getDepartmentsForOrganisation(java.lang.Long)
	 */
	@Override
	@RequestMapping(value="getDepartmentsForOrganisation", method=RequestMethod.POST)	
	public ResponseEntity <List <Department>> getDepartmentsForOrganisation(RequestEntity<Long> requestEntity) {
		Long organisationId = requestEntity.getBody();
		List <Department> departmentsList = empService.getDepartmentsForOrganisation(organisationId);
		ResponseEntity<List<Department>> responseEntity;
		if(departmentsList.isEmpty()) {
			responseEntity = new ResponseEntity<List<Department>>(HttpStatus.NO_CONTENT);
		}else {
			responseEntity = new ResponseEntity<List<Department>>(departmentsList, HttpStatus.OK);	
		}		
		return responseEntity;
	}

	@Override
	@RequestMapping(value="getOrganisations", method=RequestMethod.GET)
	public ResponseEntity <List <Organisation>>getOrganisations() {
		List <Organisation>  organisationList = empService.getOrganisations();
		ResponseEntity<List<Organisation>> responseEntity;
		if(organisationList.isEmpty()) {
			responseEntity = new ResponseEntity<List<Organisation>>(HttpStatus.NO_CONTENT);
		}else {
			responseEntity = new ResponseEntity<List<Organisation>>(organisationList, HttpStatus.OK);	
		}		
		return responseEntity;
	}
}
