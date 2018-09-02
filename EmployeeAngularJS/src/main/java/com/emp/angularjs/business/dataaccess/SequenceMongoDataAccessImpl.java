/**
 * 
 */
package com.emp.angularjs.business.dataaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.emp.angularjs.business.entity.Sequence;

/**
 * @author Tripti
 *
 */
@Repository
public class SequenceMongoDataAccessImpl implements SequenceMongoDataAccess {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	/* (non-Javadoc)
	 * @see com.emp.angularjs.business.dataaccess.SequenceMongoDataAccess#generateNextValue(java.lang.String)
	 */
	@Override
	public Long generateNextValue(String key) {
		//get sequence id
		  Query query = new Query(Criteria.where("seqType").is(key));

		  //increase sequence id by 1
		  Update update = new Update();
		  update.inc("sequenceValue", 1);

		  //return new increased id
		  FindAndModifyOptions options = new FindAndModifyOptions();
		  options.returnNew(true);

		  //this is the magic happened.
		  Sequence sequence = 
	            mongoTemplate.findAndModify(query, update, options, Sequence.class);

		  //if no id, throws SequenceException
	          //optional, just a way to tell user when the sequence id is failed to generate.
/*		  if (seqId == null) {
			throw new SequenceException("Unable to get sequence id for key : " + key);
		  }*/

		  return sequence.getSequenceValue();
	}
}
