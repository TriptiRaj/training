/**
 * 
 */
package com.hello.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller class for handling requests
 * 
 * @author Tripti
 */

/**
 * Method to intercept /welcome requests.
 * 
 * @author Tripti
 */
@Controller
public class HelloSpringBootController {

	@RequestMapping("/welcome")
	public ModelAndView firstPage() {
		return new ModelAndView("/welcome");
	}
}
