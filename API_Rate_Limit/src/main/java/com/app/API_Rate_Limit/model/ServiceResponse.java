/**
 * 
 */
package com.app.API_Rate_Limit.model;

/**
 * @author CQ927DW
 *
 */
public class ServiceResponse {

	private long versionId = 1L;
	private Object resObject;
	private int status;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getResMessage() {
		return resMessage;
	}
	public void setResMessage(String resMessage) {
		this.resMessage = resMessage;
	}
	
	
	
}
