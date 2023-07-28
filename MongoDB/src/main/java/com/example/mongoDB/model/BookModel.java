/**
 * 
 */
package com.example.mongoDB.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aritra
 *
 */
@Document(collection = "Books")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookModel {
	@Id
	@Indexed(unique = true) //makes the below parameter unique, if an existing id is used to save a new entry it will not get saved.
	private int id;
	
	private String name;
	private String author; 
	private String dateOfAdding;
	
}
