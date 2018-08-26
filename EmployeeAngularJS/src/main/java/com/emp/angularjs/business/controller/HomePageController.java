/**
 * 
 */
package com.emp.angularjs.business.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Tripti
 *
 */
//@Controller
public class HomePageController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayHomePage() {
		return "employeeAngularJS";
	}
}
