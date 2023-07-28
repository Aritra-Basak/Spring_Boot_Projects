package com.example.mongoDB.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongoDB.model.BookModel;
import com.example.mongoDB.model.ServiceResponse;
import com.example.mongoDB.service.BookService;


@RestController
@RequestMapping(value="/myBook")
public class BookController {
	@Autowired
	BookService bs;
	
/***Method to save a new book in the book library*/
@PostMapping(value="/insert")
public ServiceResponse saveMyBook(@RequestBody BookModel bm)
{
	//throw new MissingServletRequestParameterException("xyz", "String");
	return bs.saveMyBook(bm);
}

/***Method to get all, or specific book details from the book library*/
@GetMapping(value="/getMyBook")
public ServiceResponse getMyBook(@RequestParam(required = false) String name, @RequestParam(required = false) String author,@RequestParam(required = false, defaultValue = "0") int id)
{
	return bs.findMyBook(name, author, id);
}

/***Method to remove all new book from the book library*/
@DeleteMapping("/removeMyBook")
public ServiceResponse deleteAllBooks()
{
	return bs.deleteAllBooks();
}

/***Method to remove a particular book from the book library*/
@DeleteMapping("/removeThisBook")
public ServiceResponse deleteThisBook(@RequestParam(required = false, defaultValue = "0") int id)
{
	
	return bs.deleteThisBook(id);
}

@GetMapping(value="/countBooks")
public ServiceResponse countBooks()
{
	return bs.countMyBooks();
}

}
