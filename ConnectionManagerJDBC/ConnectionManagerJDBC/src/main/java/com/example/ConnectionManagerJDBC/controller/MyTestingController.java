/**
 * 
 */
package com.example.ConnectionManagerJDBC.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ConnectionManagerJDBC.model.MyTestingEntity;
import com.example.ConnectionManagerJDBC.service.MyTestingService;

/**
 * @author Aritra
 *
 */
@RestController
@RequestMapping(value="/entity")
public class MyTestingController {
	
	@Autowired
	MyTestingService service;
	
	@GetMapping(value="/getAll")
	public ResponseEntity<?> getALl(){
		return new ResponseEntity<List<MyTestingEntity>>(service.getAll(),HttpStatus.OK);
	}
	@PostMapping(value="/create")
	public ResponseEntity<?> saveALl(@Valid @RequestBody MyTestingEntity entity){
		try{
			service.create(entity);
			return new ResponseEntity<String>("Successfully Created",HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.toString(),HttpStatus.CREATED);
			
		}
		
	}
	@GetMapping(value="/selected")
	public ResponseEntity<?>  selected(@RequestParam(required = true)  int id){
		return new ResponseEntity<MyTestingEntity>(service.selected(id),HttpStatus.OK);
	}
	
	//For showing error while calling an API.
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}

}
