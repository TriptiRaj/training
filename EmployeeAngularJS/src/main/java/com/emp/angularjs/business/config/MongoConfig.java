/**
 * 
 */
package com.emp.angularjs.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.emp.angularjs.business.dataaccess.SequenceMongoDataAccessImpl;
import com.mongodb.MongoClient;

/**
 * @author Tripti
 * 
 * Provides connection to Mongo DB
 */
@Configuration
@ComponentScan(basePackages = "com.emp.angularjs.business.config")
@EnableMongoRepositories("com.emp.angularjs.business.repository")
public class MongoConfig {
	
	/**
	 * Creates an instance of MongoDatabase.
	 * 
	 * @return	MongoDB instance
	 */
/*	@Bean
	public static MongoDatabase getMongoDB() {
		MongoDatabase db = null;
		if(db == null) {
			// Create a CodecRegistry containing the PojoCodecProvider instance.
			CodecProvider pojoCodecProvider = PojoCodecProvider.builder().register("com.emp.angularjs.business.entity").build();
			CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
			
			MongoClient mongoClient = new MongoClient("localhost",MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
			db = mongoClient.getDatabase("EmpDB");			
		}

		return db;
	}
*/	
	/**
	 * Mongo DB Data access class
	 * 
	 * @return 		MongoDBDataAccess instance
	 */
/*	@Bean
	public MongoDBDataAccess getDataAccess () {
		return new MongoDBDataAccess();
	}
*/	
	/**
	 * Mongo DB with Spring Data, Data access class
	 * 
	 * @return 		SequenceMongoDataAccessImpl instance
	 */
	@Bean
	public SequenceMongoDataAccessImpl getCounterMongoDataAccessImpl () {
		return new SequenceMongoDataAccessImpl();
	}
	
	/**
	 * MongoTemplate for Mongo DB with Spring Data
	 * 
	 * @return 		MongoTemplate instance
	 */
	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(new MongoClient(), "EmpDB");
	}
}
