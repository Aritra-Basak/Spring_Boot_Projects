package com.example.APISIX.model;

import java.util.Date;

public class AuthenticationResponse {
	private String token;
	private String expiredOn;
	private String tokenType;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getExpiredOn() {
		return expiredOn;
	}
	public void setExpiredOn(String expiredOn) {
		this.expiredOn = expiredOn;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	

	


}
