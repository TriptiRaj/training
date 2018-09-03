/**
 * 
 */
package com.emp.angularjs.business.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class JPAConfig {

	private static final Logger LOGGER = LogManager.getLogger(JPAConfig.class);

/*	@Bean//(name = "getEntityManager")
	public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
		final EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("EmployeePersistentUnit");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		LOGGER.info("Entity Manager succesfully created.");
		return entityManager;
	}*/
  
	@Bean
	public EntityManagerFactory entityManagerFactory() {
	    return Persistence.createEntityManagerFactory("EmployeePersistentUnit");
	  }
	
    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        //transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
