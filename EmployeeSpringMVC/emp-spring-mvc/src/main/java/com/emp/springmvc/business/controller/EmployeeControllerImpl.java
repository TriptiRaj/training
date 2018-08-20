/**
 * 
 */
package com.emp.springmvc.business.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.emp.springmvc.business.entity.Department;
import com.emp.springmvc.business.entity.Employee;
import com.emp.springmvc.business.entity.Organisation;
import com.emp.springmvc.business.service.EmployeeService;


/**
 * Implementation class for EmployeeController
 * 
 * @author Tripti
 */
@Controller
public class EmployeeControllerImpl implements EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@RequestMapping("/")
	public String displayHomePage() {
		return "empPortal";
	}

	@RequestMapping("displayAddEmpPage")
	public ModelAndView displayAddEmployeePage() {
		List <Organisation>  organisationList = empService.getOrganisations();
		ModelAndView modelAndView = new ModelAndView("addEmployee");
		modelAndView.addObject("organisationList", organisationList);
		modelAndView.addObject("employee", new Employee());
		return modelAndView;
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
	public ModelAndView addEmployee(final @ModelAttribute("employee") Employee employee) {
		ModelAndView modelAndView = new ModelAndView("addEmployeeSuccessful");
		empService.addEmployee(employee);
		return modelAndView;
	}

	/* (non-Javadoc)
	 * @see com.emp.business.controller.EmployeeController#updateEmplpoyee(com.emp.business.entity.Employee)
	 */
	@Override
	@RequestMapping(value="updateEmployeeDetails", method=RequestMethod.POST)
	public String updateEmplpoyee(@ModelAttribute ("employee")Employee employee) {
		empService.updateEmplpoyee(employee);
		return "updateEmployeeSuccessful";
	}

	/* (non-Javadoc)
	 * @see com.emp.business.controller.EmployeeController#searchEmployee(java.lang.Long, java.lang.Long)
	 */
	@Override
	@RequestMapping(value="searchByOrgAndDept", method = RequestMethod.POST)
	public ModelAndView searchEmployee(final @ModelAttribute("employee") Employee employee) {
		List <Employee> employeeList = empService.searchEmployee(employee.getOrganisationId(), employee.getDepartmentId());
		ModelAndView modelAndView = new ModelAndView("searchEmployee");
		modelAndView.addObject("employeeList", employeeList);
		return modelAndView;
	}

	/* (non-Javadoc)
	 * @see com.emp.business.controller.EmployeeController#searchEmployeeById(java.lang.Long)
	 */
	@Override
	@RequestMapping(value="searchByEmpId", method = RequestMethod.POST)
	public ModelAndView searchEmployeeById(@ModelAttribute("employee") Employee employee) {
		employee = empService.searchEmployeeById(employee.getEmployeeId());
		if(employee == null) {
			employee = new Employee();
		}
		ModelAndView modelAndView = new ModelAndView("searchEmployee");
		modelAndView.addObject("employee", employee);
		return modelAndView;
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
	@RequestMapping(value="deleteEmployee")
	public String deleteEmployee(final Long employeeId) {
		empService.deleteEmployee(employeeId);
		return "deleteEmpSuccessful";
	}

	/* (non-Javadoc)
	 * @see com.emp.business.controller.EmployeeController#getDepartmentsForOrganisation(java.lang.Long)
	 */
	@Override
	@RequestMapping(value="getDepartmentsForOrganisation", method=RequestMethod.GET)	
	public @ResponseBody List<Department> getDepartmentsForOrganisation(Long organisationId) {
		List <Department> departmentsList = empService.getDepartmentsForOrganisation(organisationId);
		return departmentsList;
	}

	@Override
	//@RequestMapping(value="getDepartmentsForOrganisation", method=RequestMethod.GET)
	public @ResponseBody List <Department> getOrganisations(Long organisationId) {
		List <Department>  departmentsList = empService.getDepartmentsForOrganisation(organisationId);
		return departmentsList;
	}
}
