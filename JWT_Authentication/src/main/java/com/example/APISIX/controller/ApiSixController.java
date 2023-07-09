package com.example.APISIX.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.APISIX.service.CreateMyBucket;
import com.example.APISIX.model.AuthenticationRequest;
import com.example.APISIX.model.ServiceResponse;
import com.example.APISIX.service.JWTService;

import io.github.bucket4j.Bucket;

@RestController
@RequestMapping(value="/apisix")
public class ApiSixController {
	@Autowired
	JWTService jwt;
	
	@Autowired
	private CreateMyBucket cmb;

	 @GetMapping(value="/hello")
	    public ServiceResponse hello(HttpServletRequest request) {
		 return jwt.verifyuser(request);
	        
	    }
	 
	 @PostMapping(value="/generateJWT")
	 public ServiceResponse myJWT(@RequestBody AuthenticationRequest authReq, HttpServletRequest request)
	 {
		 
		 Bucket bucket = cmb.resolveBucket(authReq.getUserId());
		  	   if (bucket.tryConsume(1)){
		  		   return jwt.getAuthToken(authReq, request);
			      }
	       else {
	    	   ServiceResponse response = new ServiceResponse();
			response.setResponseMessage("You have exceeded your API call limit. Please try after some time.");
			response.setStatusCode(0);
//	     	return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
			return response;
	           }
		 
	 }

	
}
