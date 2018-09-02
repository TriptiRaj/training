/**
 * 
 */
package com.emp.angularjs.business.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Tripti
 *
 */
@Document(collection = "counters")
public class Sequence {

	@Id
	private String seqId;
	
	private Long sequenceValue;
	
	private String seqType;

	/**
	 * @return the seqId
	 */
	public String getSeqId() {
		return seqId;
	}

	/**
	 * @param seqId the seqId to set
	 */
	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}

	/**
	 * @return the sequenceValue
	 */
	public Long getSequenceValue() {
		return sequenceValue;
	}

	/**
	 * @param sequenceValue the sequenceValue to set
	 */
	public void setSequenceValue(Long sequenceValue) {
		this.sequenceValue = sequenceValue;
	}

	/**
	 * @return the seqType
	 */
	public String getSeqType() {
		return seqType;
	}

	/**
	 * @param seqType the seqType to set
	 */
	public void setSeqType(String seqType) {
		this.seqType = seqType;
	}

 
}
