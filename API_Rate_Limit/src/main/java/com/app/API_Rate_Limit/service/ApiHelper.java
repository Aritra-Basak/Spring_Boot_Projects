/**
 * 
 */
package com.app.API_Rate_Limit.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;



/**
 * this function takes HttpServletRequest and validate user X-ACCESS-TOKEN
 * throws errors if there is any mismatch
 * @author Aritra
 * 
 *
 */
@Component
public class ApiHelper {
	public void tokenChecker(HttpServletRequest request) throws Exception
	{
		String xAccessToken = request.getHeader("X-ACCESS-TOKEN");//gets user Access Token through Http Request.
		if (xAccessToken == null) {
			throw new Exception("Access Token is missing in header.");
		}

		if (!xAccessToken.equals("GiveMeAccess12345")){
			throw new Exception("Access Token access is invalid. You do not have permission to access this resource, please provide proper access token");
		}
		
		
	}

}
