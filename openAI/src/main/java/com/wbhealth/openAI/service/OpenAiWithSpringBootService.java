/**
 * 
 */
package com.wbhealth.openAI.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wbhealth.openAI.model.Choice;
import com.wbhealth.openAI.model.OpenAiRequest;
import com.wbhealth.openAI.model.OpenAiResponse;
import com.wbhealth.openAI.model.ServiceResponse;
import com.wbhealth.openAI.model.TestClass;

/**
 * @author Aritra
 *
 */
@Service
public class OpenAiWithSpringBootService {
	
	@Value("${personal.openai.api.token}")
	private String personalOpenAiToken;

	@Value("${openAi.model}")
	private String openAiModel;

	@Value("${openAi.url.endpoint}")
	private String openAiEndpoint;

	@Autowired
	@Qualifier("customRestTemplate")
	private RestTemplate restTemplate;

	private static final Double TEMPERATURE = 0.3;

	private static final Double TOP_P = 1.0;

	private static final Integer MAX_TOKENS = 100;

	public ServiceResponse submitOpenAiRequest(String inputCommand, HttpServletRequest request) {
		
		String xAccessToken = request.getHeader("X-ACCESS-TOKEN");
		ServiceResponse sr = new ServiceResponse();
		if (xAccessToken == null)
		{
			sr.setStatus("Failed");
			sr.setResObject("Access token is missing");
			sr.setStatusCode(401);
			return sr;
		}
		if (!xAccessToken.equals("GiveMeAccess12345"))
		{
			sr.setStatus("Failed");
			sr.setResObject("Access token is invalid");
			sr.setStatusCode(403);
			return sr;
		}
		System.out.println("inputCommand : " + inputCommand);
		
		try {
			OpenAiRequest aiRequest = OpenAiRequest.builder().max_tokens(MAX_TOKENS).temperature(TEMPERATURE).top_p(TOP_P).model(openAiModel).prompt(inputCommand).build();
			
			ResponseEntity<OpenAiResponse> restCallResponse = restTemplate.postForEntity(openAiEndpoint, aiRequest, OpenAiResponse.class);
			if (HttpStatus.OK.equals(restCallResponse.getStatusCode())) {
				OpenAiResponse aiResponse = restCallResponse.getBody();
				List<String> respTextList = new ArrayList<String>();
				for(Choice c : aiResponse.getChoices()) {
					if (c != null) {
						String txt = c.getText().replaceAll("[\n\r]", "");
						if (txt.indexOf("?") == 0) {
							txt = txt.replace("?", "");
						}
						respTextList.add(txt);
					}
				}
				sr.setStatus("Success");
				sr.setResObject(respTextList);
				sr.setStatusCode(200);
				sr.setSearchCount(respTextList.size());
			} else {
				sr.setStatus("Failed");
				sr.setResponseMessage(restCallResponse.getBody().toString());
				sr.setStatusCode(500);
			}
		} catch (Exception e) {
			sr.setStatus("Failed");
			sr.setResponseMessage(e.getMessage());
			sr.setStatusCode(500);
		}
		return sr;
	}
}
