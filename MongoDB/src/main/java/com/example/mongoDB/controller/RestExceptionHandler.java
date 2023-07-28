/**
 * 
 */
package com.example.mongoDB.controller;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.mongoDB.util.ApiError;


/**
 * @author Aritra
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler {
//
//  @Override
//   protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//       String error = "Malformed JSON request";
//       return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
//   }
//
//   private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
//       return new ResponseEntity<>(apiError, apiError.getStatus());
//   }
//
//   @Override
//   protected ResponseEntity<Object> handleMissingServletRequestParameter(
//           MissingServletRequestParameterException ex, HttpHeaders headers,
//           HttpStatus status, WebRequest request) {
//       String error = ex.getParameterName() + " parameter is missing";
//       return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
//   }
//   @Override
//   protected ResponseEntity<Object> handleNoHandlerFoundException(
//           NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//       ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
//       apiError.setMessage(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));
//       apiError.setDebugMessage(ex.getMessage());
//       return buildResponseEntity(apiError);
//   }
//   @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//   protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
//                                                                     WebRequest request) {
//       ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
//       apiError.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
//       apiError.setDebugMessage(ex.getMessage());
//       return buildResponseEntity(apiError);
//   }
//	
	
	
	/*Generic Exception*/

    @ExceptionHandler(Exception.class)
    public HashMap<String, String> handleException(HttpServletRequest request, Exception e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());
        return response;
    }
    

    @ExceptionHandler(MissingPathVariableException.class)
    public HashMap<String, String> handleMissingPathVariableException(HttpServletRequest request, MissingPathVariableException e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Required path variable is missing in this request. Please add it to your request.");
        return response;
    }
    
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HashMap<String, String> handleNoHandlerFound(NoHandlerFoundException e, WebRequest request) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", e.getLocalizedMessage());
        return response;
    }
    
 

}