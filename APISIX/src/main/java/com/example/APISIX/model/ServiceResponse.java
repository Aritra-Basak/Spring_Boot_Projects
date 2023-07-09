package com.example.APISIX.model;

public class ServiceResponse {
	private static final long serialVersionUID = 2331307404151448988L;
	private String status;
	private String responseMessage;
	private Object resObject;
	private int statusCode;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public Object getResObject() {
		return resObject;
	}
	public void setResObject(Object resObject) {
		this.resObject = resObject;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	

}
