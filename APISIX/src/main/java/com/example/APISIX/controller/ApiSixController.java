package com.example.APISIX.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.APISIX.model.AuthenticationRequest;
import com.example.APISIX.model.ServiceResponse;
import com.example.APISIX.service.JWTService;

@RestController
@RequestMapping(value="/apisix")
public class ApiSixController {
	@Autowired
	JWTService jwt;

	 @GetMapping(value="/hello")
	    public ServiceResponse hello(HttpServletRequest request) {
		 return jwt.verifyuser(request);
	        
	    }
	 
	 @PostMapping(value="/generateJWT")
	 public ServiceResponse myJWT(@RequestBody AuthenticationRequest authReq, HttpServletRequest request)
	 {
		 return jwt.getAuthToken(authReq, request);
	 }

	
}
