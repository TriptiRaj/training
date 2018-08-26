/**
 * 
 */
package com.emp.angularjs.business.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tripti
 *
 */
@Configuration
@ComponentScan(basePackages = "com.emp.springmvc.business")
public class JPAConfig {

	private static final Logger LOGGER = LogManager.getLogger(JPAConfig.class);

	@Bean(name = "getEntityManager")
	public EntityManager getEmpEntityManager() {
		final EntityManagerFactory entityManagFactory = Persistence
				.createEntityManagerFactory("EmployeePersistentUnit");
		EntityManager entityManager = entityManagFactory.createEntityManager();
		LOGGER.info("Entity Manager succesfully created.");
		return entityManager;
	}

}
