/**
 * 
 */
package com.wbhealth.openAI.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * @author Chandan & Aritra
 *
 */
@Configuration
public class ApplicationBeanConfigurations {
    @Value("${personal.openai.api.token}")
	private String personalOpenAiToken;
    //adding the API key as an Authorization Header parameter.
	@Bean
	@Qualifier("customRestTemplate")
	public RestTemplate customRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add((request, body, execution) -> {
			request.getHeaders().add("Authorization", "Bearer " + personalOpenAiToken);
			return execution.execute(request, body);
		});		
		return restTemplate;
    
	}
    
    
}
