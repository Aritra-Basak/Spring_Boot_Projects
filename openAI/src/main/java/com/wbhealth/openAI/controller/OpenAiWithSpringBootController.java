package com.wbhealth.openAI.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wbhealth.openAI.model.ServiceResponse;
import com.wbhealth.openAI.model.UserInput;
import com.wbhealth.openAI.service.OpenAiWithSpringBootService;

@RestController
@RequestMapping(value="/SpringAi")
public class OpenAiWithSpringBootController {

	@Autowired
	OpenAiWithSpringBootService service;

	@PostMapping(value="/textMe")
	public ServiceResponse submitOpenAiRequest(@RequestBody UserInput userInput, HttpServletRequest request) {
		return service.submitOpenAiRequest(userInput.getUserCommand(),request);
	}
	
	@GetMapping(value="/about")
	public String submitOpenAiRequest() {
		return "This API is developed by Aritra";
	}
}
