/**
 * 
 */
package com.example.mongoDB.model;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Aritra
 *
 */
@Service
public class ServiceResponse {

	private long versionId = 1L;
	private Object resObject;
	private HttpStatus status;
	private String resMessage;
	
	public long getVersionId() {
		return versionId;
	}
	public void setVersionId(long versionId) {
		this.versionId = versionId;
	}
	public Object getResObject() {
		return resObject;
	}
	public void setResObject(Object resObject) {
		this.resObject = resObject;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getResMessage() {
		return resMessage;
	}
	public void setResMessage(String resMessage) {
		this.resMessage = resMessage;
	}
	
	
	
}
