/**
 * 
 */
package com.example.mongoDB.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mongoDB.model.BookModel;
import com.example.mongoDB.model.ServiceResponse;
import com.example.mongoDB.repository.BookRepository;

/**
 * @author Aritra
 *
 */
@Service
public class BookService {
	
	@Autowired
	BookRepository bp;

	@Autowired
	ServiceResponse sr;//To use the ServiceResponse with an Autowired we are declaring @Service on the ServiceResponse Class
	
	public ServiceResponse saveMyBook(BookModel bm)
	{
		
		try {
			BookModel books = bp.save(bm); // this will add and return us the added data
			sr.setResMessage("Successfully Added Your Book into the library");
			sr.setResObject(books);
			sr.setStatus(HttpStatus.CREATED);
			return sr;
		}
		catch(Exception e)
		{
			sr.setResMessage("Could not insert the book into the library");
			sr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			 return sr;
		}
	}
	public ServiceResponse findMyBook(String name,String author, int id )
	{
		ServiceResponse sr = new ServiceResponse();
		try {
		    List<BookModel> books = new ArrayList<BookModel>();

		    if (name == null && author==null && id==0 )
		      bp.findAll().forEach(books::add);
		    else if(name != null && author==null && id==0)
		      bp.findByname(name).forEach(books::add);
		    else if(name == null && author!=null && id==0)
			      bp.findByAuthor(author).forEach(books::add);
		    else if(name == null && author==null && id!=0)
			      bp.findById(id).forEach(books::add);
		    
		    System.out.println(books);
		    if (books.isEmpty()) {
		    	sr.setResMessage("Could not find the book in the library");
				sr.setStatus(HttpStatus.NO_CONTENT);
				return sr;
		    }
		    
		    sr.setResMessage("Successfully Found Your Book into the library");
			sr.setResObject(books);
			sr.setStatus(HttpStatus.OK);
		    return sr;
		  } catch (Exception e) {
		    	sr.setResMessage("Error : Could not find your Book or Books. Java Error: "+e.toString());
				sr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		    return sr;
		  }
	}

	/*public ResponseEntity<List<BookModel>> findMyBook(String name,String author, int id )
	{
		
		try {
		    List<BookModel> books = new ArrayList<BookModel>();

		    if (name == null && author==null && id==0 )
		      bp.findAll().forEach(books::add);
		    else if(name != null && author==null && id==0)
		      bp.findByname(name).forEach(books::add);
		    else if(name == null && author!=null && id==0)
			      bp.findByAuthor(author).forEach(books::add);
		    else if(name == null && author==null && id!=0)
			      bp.findById(id).forEach(books::add);
		    
		    System.out.println(books);
		    if (books.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    
		    sr.setResMessage("Successfully Found Your Book into the library");
			sr.setResObject(books);
			sr.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(books,HttpStatus.OK);
		  } catch (Exception e) {
		    	sr.setResMessage("Error :"+e.toString());
				sr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  }
	}*/

	
	public ServiceResponse deleteThisBook(int id) {
		
	  try {
	    bp.deleteById(id);
	    String responseBody="Successfully Deleted the Book with the id:"+id;
	    sr.setResMessage(responseBody);
	    sr.setStatus(HttpStatus.OK);
	    return sr;
	  } catch (Exception e) {
		  sr.setResMessage("Error :"+e.toString());
			sr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	    return sr;
	  }
	}

	
	public ServiceResponse deleteAllBooks() {
		
	  try {
	    bp.deleteAll();
	    String responseBody="Successfully Deleted all the Books from the library";
	    sr.setResMessage(responseBody);
	    sr.setStatus(HttpStatus.OK);
	    return sr;
	  } catch (Exception e) {
		  sr.setResMessage("Error :"+e.toString());
			sr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	    return sr;
	  }
	}
	
	public ServiceResponse countMyBooks()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		try
		{
			Long countBooks=bp.count();
		    String responseBody="Successfully Counted all the Books in the library";
		    sr.setResMessage(responseBody);
		    sr.setResObject("Number of Books in the Library on "+dtf.format(now)+" is: "+countBooks);
		    sr.setStatus(HttpStatus.OK);
		    return sr;
		}
		catch (Exception e) {
			  sr.setResMessage("Error :"+e.toString());
				sr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		    return sr;
		  }
	}
}
