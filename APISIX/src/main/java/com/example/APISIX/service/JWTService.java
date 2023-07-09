package com.example.APISIX.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.example.APISIX.model.AuthenticationRequest;
import com.example.APISIX.model.AuthenticationResponse;
import com.example.APISIX.model.ServiceResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class JWTService {
	
		private static final String SECRETY_KEY = "sEcrEtkEy";
	    private final long expirationTime = 1000 * 60 * 60 *24; // Token expiration time (in milliseconds)

	    /**
	     * @author Aritra Basak.
	     * Service Method to generate JWT Token based on username and password.
	     *
	     */

	    
	    public ServiceResponse getAuthToken(AuthenticationRequest auth, HttpServletRequest request) {
	    	String xAccessToken = request.getHeader("X-ACCESS-TOKEN");
	    	ServiceResponse response = new ServiceResponse();
	    	if(xAccessToken.equals("give-me-jwt"))
	    	{
	    		if((auth.getUserName()==""||auth.getUserName().isEmpty())&&(auth.getPassword()==""||auth.getPassword().isEmpty()))
	    		{
	    			response.setResponseMessage("Username or Password cannot be empty");
					response.setStatus("Not Found");
					response.setStatusCode(404);
					return response;
	    		}
	    	String jwttoken = generateToken(auth.getUserName(),auth.getPassword());
	    	Date expiry = getTokenExpirationDate(jwttoken);
	    	
			AuthenticationResponse authResponse = new AuthenticationResponse();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			authResponse.setToken(jwttoken);
			authResponse.setExpiredOn(sdf.format(expiry));
			authResponse.setTokenType("Bearer");
			response.setResObject(authResponse);
			response.setResponseMessage("Your JWT Token generated successfully");
			response.setStatus("Success");
			response.setStatusCode(200);
	    }
	    	if(!xAccessToken.equals("give-me-jwt"))
	    	{
	    		response.setResponseMessage("Your API key is not matching");
				response.setStatus("Not Found");
				response.setStatusCode(404);
	    	}
	    	if(xAccessToken.isEmpty())
	    	{
	    		response.setResponseMessage("Your API key is missing");
				response.setStatus("Failed");
				response.setStatusCode(401);
	    	}
	    	return response;
	    }

	    
	    public String generateToken(String username, String password) {
	        return Jwts.builder()
	                .setSubject(username)
	                .claim("password", password)
	                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
	                .signWith(SignatureAlgorithm.HS256, SECRETY_KEY)
	                .compact();
	    }
	    /**
	     * 
	     * Service Method to generate JWT Token expiry date.
	     *
	     */
	    public Date getTokenExpirationDate(String token) {
	        Claims claims = Jwts.parser().setSigningKey(SECRETY_KEY).parseClaimsJws(token).getBody();
	        return claims.getExpiration();
	    }
	    
	    public ServiceResponse verifyuser(HttpServletRequest request)
	    {
	    	  String authorizationHeader = request.getHeader("Authorization");
	          String token = null;
	          ServiceResponse response = new ServiceResponse();
	          if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	              token = authorizationHeader.substring(7); // Excluding "Bearer " prefix
	          }
	          //Extracting the username and the password from the JWT token
	          Claims claims = Jwts.parser().setSigningKey(SECRETY_KEY).parseClaimsJws(token).getBody();
	          String username =claims.getSubject();
	          String userpassword =(String)claims.get("password");
	          if(!username.isEmpty() && userpassword.equals("key@1234"))
	          {
	        	  	String msg ="Hello! "+username+" hope you are having a nice day. ~ SpringBoot";
	        	  	response.setResObject(msg);
	        	  	response.setResponseMessage("You are authorized successfully");
					response.setStatus("Success");
					response.setStatusCode(200);
	          }
	          else
	          {
	        	  	response.setResponseMessage("Sorry you are not authorized");
					response.setStatus("Failed");
					response.setStatusCode(500);
	          }
	          return response;
	    }
	   

}
