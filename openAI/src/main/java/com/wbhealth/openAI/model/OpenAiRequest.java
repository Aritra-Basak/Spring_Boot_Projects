package com.wbhealth.openAI.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class OpenAiRequest {
    private String model;
	private String prompt;
//	@JsonProperty("max_tokens")
	private int max_tokens;
	private double temperature;
//	@JsonProperty("top_p")
	private double top_p;
    
}
    //@JsonProperty is used to specify the property name in a JSON object when serializing or deserializing an object using Jackson. 
    //This annotation is used to map the property name in the Java object to the property name in the JSON object.
