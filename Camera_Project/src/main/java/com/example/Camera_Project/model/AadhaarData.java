/**
 * 
 */
package com.example.Camera_Project.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author CQ927DW
 *
 */
@Data
public class AadhaarData {

	@JsonProperty(value="Name")
	private String name;
	
	@JsonProperty(value="Gender")
	private String gender;
	
	@JsonProperty(value="DOB")
	private String dob;
	
	@JsonProperty(value="AadharNumber")
	private String aadhaarNumber;
	
	
}
