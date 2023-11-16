/**
 * 
 */
package com.example.ConnectionManagerJDBC.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;

/**
 * @author Aritra
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
public class MyTestingEntity {
	int id;
	@NotBlank(message="name cannot be blank")
	String name;
	//@NotBlank doesn't work with non String field.
	@NotNull(message="age cannot be null")
	@Min(value=1)
	@Max(value=100)
	int age;

}
