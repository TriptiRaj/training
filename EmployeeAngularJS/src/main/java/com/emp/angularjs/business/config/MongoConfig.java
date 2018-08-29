/**
 * 
 */
package com.emp.angularjs.business.config;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.emp.angularjs.business.dataaccess.MongoDBDataAccess;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoDatabase;

/**
 * @author Tripti
 * 
 * Provides connection to Mongo DB
 */
@Configuration
@ComponentScan(basePackages = "com.emp.springmvc.business")
public class MongoConfig {
	
	/**
	 * Creates an instance of MongoDatabase.
	 * 
	 * @return	MongoDB instance
	 */
	@Bean
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
	
	/**
	 * Mongo DB Data access class
	 * 
	 * @return 		MongoDBDataAccess instance
	 */
	@Bean
	public MongoDBDataAccess getDataAccess () {
		return new MongoDBDataAccess();
	}
}
