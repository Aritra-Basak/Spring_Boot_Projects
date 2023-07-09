/**
 * 
 */
package com.app.API_Rate_Limit.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.app.API_Rate_Limit.model.Area;
import com.app.API_Rate_Limit.model.Perimeter;
import com.app.API_Rate_Limit.model.ServiceResponse;
import com.app.API_Rate_Limit.model.Weather_Model;
import com.app.API_Rate_Limit.model.Dimension;
import com.app.API_Rate_Limit.service.CreateMyBucket;
import com.app.API_Rate_Limit.service.ApiHelper;
import io.github.bucket4j.Bucket;


/**
 * @author Aritra
 *
 */
@RestController
@RequestMapping(value="/apiRateLimit")
public class ApiController {
	
	@Autowired
	private CreateMyBucket cmb;
	
	@Autowired
	private ApiHelper apihelper;
    /***
     * API To find the perimeter of the rectangle.
     * */
    @PostMapping(value = "/api/v1/perimeter/rectangle")
    public ResponseEntity<ServiceResponse> rectangle(@RequestBody Dimension dimensions) {
    	
    	//here we are checking whether the request is allowed by consuming a token from the bucket using the method tryConsume. 
    	//If we've reached the limit, we can reject the request by responding with an HTTP 429 Too Many Requests status:
    	   ServiceResponse response = new ServiceResponse();
    	   Bucket bucket = cmb.resolveBucket(dimensions.getUserId());
    	   if (bucket.tryConsume(1)){
    		 response.setResObject(new Perimeter("rectangle",(double) 2 * (dimensions.getLength() + dimensions.getBreadth())));
    		 response.setStatus(1);
    		 response.setResMessage("Success");
	       }
	       else {
			response.setResMessage("You have exceeded your API call limit. Please try after some time.");
			response.setStatus(0);
//	     	return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
	           }
    	 
    	return ResponseEntity.ok(response);
    }
    
    //API with Access Token.
    /***
     * API To find the area of the rectangle.
     * */
    @PostMapping(value = "/api/v1/area/rectangle")
    public ResponseEntity<ServiceResponse> areaRectangle(@RequestBody Dimension dimensions,HttpServletRequest request){
    	//@RequestBody defines that the request will contain an object's value as an information
    	//request contains the web request.
    	ServiceResponse response = new ServiceResponse();
    	   try {
			apihelper.tokenChecker(request);
			Bucket bucket = cmb.resolveBucket(dimensions.getUserId());
			 // tries to consume a token from the bucket
		 	   if (bucket.tryConsume(1)) //1 token or API per call
		 	   {
		 		 response.setResObject(new Area("rectangle",(double) (dimensions.getLength() * dimensions.getBreadth())));
		 		 response.setStatus(1);
		 		 response.setResMessage("Success");
	       }
	       else {
			response.setResMessage("You have exceeded your API call limit. Please try after some time.");
			response.setStatus(0);
	           }
		} catch (Exception e) {
			
			e.printStackTrace();
			response.setResMessage(e.toString().substring(e.toString().indexOf(":")+2));
			response.setStatus(0);
		}
    	   return ResponseEntity.ok(response); 
   		
}
    /***
     * API To find the area of the triangle.
     * */
    @PostMapping(value="/api/v1/area/triangle")
    public ResponseEntity<ServiceResponse> areaTriangle(@RequestBody Dimension dimensions,HttpServletRequest request){
    	ServiceResponse response = new ServiceResponse();
 	   try {
			apihelper.tokenChecker(request);
			Bucket bucket = cmb.resolveBucket(dimensions.getUserId());
		 	   if (bucket.tryConsume(1)){
		 		 response.setResObject(new Area("Triangle",(double) 0.5*(dimensions.getHeight() * dimensions.getBase())));
		 		 response.setStatus(1);
		 		 response.setResMessage("Success");
	       }
	       else {
			response.setResMessage("You have exceeded your API call limit. Please try after some time.");
			response.setStatus(0);
	           }
		} catch (Exception e) {
			
			e.printStackTrace();
			response.setResMessage(e.toString().substring(e.toString().indexOf(":")+2));
			response.setStatus(0);
		}
 	   return ResponseEntity.ok(response); 
		
    }
    /***
     * API To find the area of the circle.
     * */
    @PostMapping(value="/api/v1/area/circle")
    public ResponseEntity<ServiceResponse> areaCircle(@RequestBody Dimension dimensions,HttpServletRequest request){
    	ServiceResponse response = new ServiceResponse();
 	   try {
			apihelper.tokenChecker(request);
			Bucket bucket = cmb.resolveBucket(dimensions.getUserId());
		 	   if (bucket.tryConsume(1)){
		 		 response.setResObject(new Area("Circle",(double) 3.14*(dimensions.getRadius() * dimensions.getRadius())));
		 		 response.setStatus(1);
		 		 response.setResMessage("Success");
	       }
	       else {
			response.setResMessage("You have exceeded your API call limit. Please try after some time.");
			response.setStatus(0);
	           }
		} catch (Exception e) {
			
			e.printStackTrace();
			response.setResMessage(e.toString().substring(e.toString().indexOf(":")+2));
			response.setStatus(0);
		}
 	   return ResponseEntity.ok(response); 
		
    }
   /***
    * API To fetch the weather report of the given input city.
    * */
    @GetMapping(value="api/myweather")
    public ResponseEntity<String> myWeather(@RequestBody Weather_Model wm,HttpServletRequest request) {
    	ResponseEntity<String> myResponseEntity = null;
    	try {
				apihelper.tokenChecker(request);
				Bucket bucket = cmb.resolveBucket(wm.getUserId());
				if (bucket.tryConsume(1)){
		    	 // Make an HTTP request to another URL
				        RestTemplate restTemplate = new RestTemplate();
				        ResponseEntity<String> response = restTemplate.getForEntity("http://api.openweathermap.org/data/2.5/weather?q="+wm.getCity()+"&units=metric&APPID=6801f552c9a5793f0ef66d28a7c4f5d9", String.class);
				        
				        // Get the response body, status code, and headers
				        String responseBody = response.getBody();
				        HttpStatus statusCode = response.getStatusCode();
				   
				        
				        // Create a new ResponseEntity with the retrieved values
				         myResponseEntity = new ResponseEntity<>(responseBody, statusCode);
			 	   }
		         else {
		        	 String responseBody = "You have exceeded your API call limit. Please try after some time.";
					 HttpStatus statusCode = HttpStatus.FORBIDDEN;
					 myResponseEntity = new ResponseEntity<>(responseBody, statusCode);
		         }
		         
    	} catch (Exception e) {
			
				e.printStackTrace();
				 String responseBody = e.toString().substring(e.toString().indexOf(":")+2);
				 HttpStatus statusCode = HttpStatus.FORBIDDEN;
				 myResponseEntity = new ResponseEntity<>(responseBody, statusCode);
		}
        
        // Return the ResponseEntity as the response from your controller
        return myResponseEntity;
    }
    
}
