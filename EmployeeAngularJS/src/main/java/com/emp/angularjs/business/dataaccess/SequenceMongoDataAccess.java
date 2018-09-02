/**
 * 
 */
package com.emp.angularjs.business.dataaccess;

/**
 * @author Tripti
 *
 */
public interface SequenceMongoDataAccess {

	Long generateNextValue(String key);
}
