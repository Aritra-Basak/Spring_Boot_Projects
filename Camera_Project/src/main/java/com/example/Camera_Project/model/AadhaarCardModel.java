package com.example.Camera_Project.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AadhaarCardModel {
	private String name;
	private String gender;
	private String dob;
	private String aadhaarNumber;
}
