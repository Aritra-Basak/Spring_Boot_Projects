package com.example.mongoDB.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mongoDB.model.BookModel;
//Below we mention MongoRepository that we will work with BookModel data and which have an id of type Integer.
//Now we can use MongoRepository’s methods: save(), findOne(), findById(), findAll(), count(), delete(), deleteById()… without implementing these methods.
public interface BookRepository extends MongoRepository<BookModel, Integer>{
	
		
		List<BookModel> findByname(String name);
		
		List<BookModel> findByAuthor(String author);
		
		List<BookModel> findById(int id);
		
		void deleteById(int id);

}
