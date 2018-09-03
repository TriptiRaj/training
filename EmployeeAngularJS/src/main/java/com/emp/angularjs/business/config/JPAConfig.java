/**
 * 
 */
package com.emp.angularjs.business.config;

import javax.persistence.EntityManagerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Tripti
 *
 */
@Configuration
@ComponentScan(basePackages = "com.emp.angularjs.business")
@EnableJpaRepositories("com.emp.angularjs.business.repository")
@EnableTransactionManagement
public class JPAConfig {

	private static final Logger LOGGER = LogManager.getLogger(JPAConfig.class);

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LOGGER.debug("Creating EntityManger");
		LOGGER.info("Creating EntityManger");
	    return Persistence.createEntityManagerFactory("EmployeePersistentUnit");
	  }
	
    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
		LOGGER.debug("Created Transaction Manager");
		LOGGER.info("Created Transaction Manager");
        return transactionManager;
    }

}
