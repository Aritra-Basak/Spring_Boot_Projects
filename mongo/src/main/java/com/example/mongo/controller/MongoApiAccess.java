/**
 * 
 */
package com.example.mongo.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongo.model.CollectionWiseDocumentCount;
import com.example.mongo.model.ProjectConstants;
import com.example.mongo.model.ServiceResponse;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

/**
 * @author ChandanSarkar
 *
 */
@RestController
public class MongoApiAccess {
	
	@Value("${mongo.db.uri}")
	String connectionString;
	
	@Value("${default.db.name}")
	String defaultDbName;
	/**
	 * For testing if a proper connection is made with the Mongo DataBase.
	 *
	 */
	@GetMapping("/test")
	public ResponseEntity<ServiceResponse> testAPI() {
		ServiceResponse response = new ServiceResponse();
		response.setResMsg("Mongo API is up and running");
		response.setStatus(ProjectConstants.SUCCESS_RESPONSE);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	/**
	 * For getting the list of Data Base available in the Mongo Server.
	 *
	 */
	@GetMapping("/getDbList")
	public ServiceResponse getDbList() {
		ServiceResponse response = new ServiceResponse();
		try {
	        MongoClient mongoClient = MongoClients.create(connectionString);
            List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
            mongoClient.close(); //closing the connection with the mongoDB
            response.setResObject(databases); 
			response.setResMsg("Fetched successfully");
			response.setStatus(ProjectConstants.SUCCESS_RESPONSE);			
		} catch (Exception e) {
			e.printStackTrace();
			response.setResMsg(e.getMessage());
			response.setStatus(ProjectConstants.FAILURE_RESPONSE);
		}
		return response;
	}
	/**
	 * For getting the list of collections in the requested DB
	 *
	 */
	@GetMapping("/getCollectionList")
	public ServiceResponse getCollectionList(@RequestParam String dbName) {
		ServiceResponse response = new ServiceResponse();
		try {
	        MongoClient mongoClient = MongoClients.create(connectionString);
            MongoIterable<String> collections = mongoClient.getDatabase(dbName).listCollectionNames();
            
            response.setResObject(collections); 
			response.setResMsg("Fetched successfully");
			response.setStatus(ProjectConstants.SUCCESS_RESPONSE);	
//			mongoClient.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.setResMsg(e.getMessage());
			response.setStatus(ProjectConstants.FAILURE_RESPONSE);
		}
		return response;
	}
	/**
	 * For getting the count of documents in the mentioned DataBase's Collection.
	 *
	 */
	@GetMapping("/getCollectionWiseDocumentCount")
	public ServiceResponse getCollectionWiseDocumentCount(@RequestParam String dbName) {
		ServiceResponse response = new ServiceResponse();
		try {
	        MongoClient mongoClient = MongoClients.create(connectionString);
	        MongoDatabase db = mongoClient.getDatabase(dbName);
            MongoIterable<String> collections = db.listCollectionNames();
            List<CollectionWiseDocumentCount> dataList = new ArrayList<CollectionWiseDocumentCount>();
            for(String collection : collections) {
            	CollectionWiseDocumentCount data = new CollectionWiseDocumentCount();
            	data.setName(collection);
            	data.setCount(db.getCollection(collection).countDocuments());
            	dataList.add(data);
            }
           
            response.setResObject(dataList); 
			response.setResMsg("Fetched successfully");
			response.setStatus(ProjectConstants.SUCCESS_RESPONSE);	
//			mongoClient.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.setResMsg(e.getMessage());
			response.setStatus(ProjectConstants.FAILURE_RESPONSE);
		}
		return response;
	}
	/**
	 * For getting the all the details of certain API from DataBase's Collection, which have a particular name provided by user.
	 *
	 */	
	@GetMapping("/getRequestListByKeyValue")
	public ServiceResponse getCollectionWiseDocumentCount(@RequestParam String collectionName, @RequestParam String jsonKey, @RequestParam String jsonValue) {
		ServiceResponse response = new ServiceResponse();
		try {
	        MongoClient mongoClient = MongoClients.create(connectionString);
	        List<Document> documents = mongoClient.getDatabase(defaultDbName).getCollection(collectionName).find(new Document(jsonKey, jsonValue)).into(new ArrayList<Document>());
	       // mongoClient.close();
            response.setResObject(documents);
            response.setSearchCount(documents.size());
			response.setResMsg("Fetched successfully");
			response.setStatus(ProjectConstants.SUCCESS_RESPONSE);			
		} catch (Exception e) {
			e.printStackTrace();
			response.setResMsg(e.getMessage());
			response.setStatus(ProjectConstants.FAILURE_RESPONSE);
		}
		return response;
	}
	/**
	 * For getting the count of each APIs from the mentioned DataBase's Collection. And sorting them in accordance of ascending order of the no. of calls made to them.
	 *
	 */
	@GetMapping("/getApiWiseRequestCount")
	public ServiceResponse getApiWiseRequestCount(@RequestParam String collectionName, 
			@RequestParam (required=false, defaultValue="") String sortBy, 
			@RequestParam (required=false, defaultValue="0") int sortOrder) {
		ServiceResponse response = new ServiceResponse();
		try {
			 
				MongoClient client = MongoClients.create();
				List<Bson> pipeline = new ArrayList<Bson>();
				//Performing Aggregation Pipeline with staging.  
				Document projectSpec = new Document("$project", new Document("apiUrl", 1L)); //Stage 1- showing only the API urls of the from the collection
				pipeline.add(projectSpec);//adding the staging(Stage 1) in the pipeline
				Document groupSpec = new Document("$group", 
					    new Document("_id", "$apiUrl") //Stage 2- grouping the collection w.r.t the count of no.of API calls
					            .append("noOfApiCall", 
					    new Document("$count", 
					    new Document())));
				pipeline.add(groupSpec);//adding the staging(Stage 2) in the pipeline
				if (!sortBy.isEmpty()) {
					if((!(sortOrder==0) && (sortOrder == 1 || sortOrder == -1))) {
					Document sortSpec = new Document("$sort", new Document(sortBy, sortOrder)); //Stage 3-Sorting the Collection w.r.t to no. of API calls 
					pipeline.add(sortSpec);//adding the staging(Stage 3) in the pipeline
					//sortBy=noOfApiCall
					//sortOrder=1
					} else
					{
						throw new Exception("Invalid Sort Order. Only 1 or -1 is allowed.");
					}
				}
				List<Document> dataList = client.getDatabase(defaultDbName).getCollection(collectionName).aggregate(pipeline).into(new ArrayList<Document>());
				client.close();
				response.setResObject(dataList);
	            response.setSearchCount(dataList.size());
				response.setResMsg("Fetched successfully");
				response.setStatus(ProjectConstants.SUCCESS_RESPONSE);

		} catch (Exception e) {
			e.printStackTrace();
			response.setResMsg(e.getMessage());
			response.setStatus(ProjectConstants.FAILURE_RESPONSE);
		}
		return response;
	}
	/**
	 * For getting the count of a single API, whose name is passed as a parameter in the request from the mentioned DataBase's Collection.
	 *
	 */
	@GetMapping("/getSingleApiRequestCount")
	public ServiceResponse getSingleApiRequestCount(@RequestParam String collectionName,@RequestParam (required=false, defaultValue="") String apiName) {
		ServiceResponse response = new ServiceResponse();
		try {
			 
				MongoClient client = MongoClients.create();
				List<Bson> pipeline = new ArrayList<Bson>();
				//Performing Aggregation Pipeline with staging.  
				Document projectSpec = new Document("$project", new Document("apiUrl", 1L)); //Stage 1- showing only the API urls of the from the collection
				pipeline.add(projectSpec);
				Document groupSpec = new Document("$group", 
					    new Document("_id", "$apiUrl") //Stage 2- grouping the collection w.r.t the count of no.of API calls
					            .append("noOfApiCall", 
					    new Document("$count", 
					    new Document())));
				pipeline.add(groupSpec);
				if (!apiName.isEmpty()) {
					Document sortSpec = new Document("$match", new Document("_id", apiName)); //Stage 3-Sorting the Collection w.r.t to API Name 
					pipeline.add(sortSpec);
					} else
					{
						throw new Exception("Invalid: API name cannot be blank.");
					}
				
				List<Document> dataList = client.getDatabase(defaultDbName).getCollection(collectionName).aggregate(pipeline).into(new ArrayList<Document>());
				client.close();
				response.setResObject(dataList);
	            response.setSearchCount(dataList.size());
				response.setResMsg("Fetched successfully");
				response.setStatus(ProjectConstants.SUCCESS_RESPONSE);

		} catch (Exception e) {
			e.printStackTrace();
			response.setResMsg(e.getMessage());
			response.setStatus(ProjectConstants.FAILURE_RESPONSE);
		}
		return response;
	}
	/**
	 * For getting all the API calls, which have a success response status.
	 *
	 */
	@GetMapping("/getSuccessApis")
	public ServiceResponse getSuccessApis(@RequestParam String collectionName) {
		ServiceResponse response = new ServiceResponse();
		try {
			 
				MongoClient client = MongoClients.create();
				List<Bson> pipeline = new ArrayList<Bson>();
				//Performing Aggregation Pipeline with staging.  
				Document projectSpec =new Document("$project", 
					    new Document("apiUrl", 1L)
			            .append("response.status", 1L)
			            .append("logTime", 1L)
			            .append("ip", 1L)); 
				pipeline.add(projectSpec);
				Document groupSpec =  new Document("$match",new Document("response.status", "Success"));
				pipeline.add(groupSpec);			
				List<Document> dataList = client.getDatabase(defaultDbName).getCollection(collectionName).aggregate(pipeline).into(new ArrayList<Document>());
				client.close();
				response.setResObject(dataList);
	            response.setSearchCount(dataList.size());
				response.setResMsg("Fetched successfully");
				response.setStatus(ProjectConstants.SUCCESS_RESPONSE);

		} catch (Exception e) {
			e.printStackTrace();
			response.setResMsg(e.getMessage());
			response.setStatus(ProjectConstants.FAILURE_RESPONSE);
		}
		return response;
	}
	/**
	 * For getting all the API calls, which have a failed response status.
	 *
	 */
	@GetMapping("/getFailedApis")
	public ServiceResponse getFailedApis(@RequestParam String collectionName) {
		ServiceResponse response = new ServiceResponse();
		try {
			 
				MongoClient client = MongoClients.create();
				List<Bson> pipeline = new ArrayList<Bson>();
				//Performing Aggregation Pipeline with staging.  
				Document projectSpec =new Document("$project", 
					    new Document("apiUrl", 1L)
			            .append("response.status", 1L)
			            .append("logTime", 1L)
			            .append("ip", 1L)); 
				pipeline.add(projectSpec);
				Document groupSpec =  new Document("$match",new Document("response.status", "Failed"));
				pipeline.add(groupSpec);			
				List<Document> dataList = client.getDatabase(defaultDbName).getCollection(collectionName).aggregate(pipeline).into(new ArrayList<Document>());
				client.close();
				response.setResObject(dataList);
	            response.setSearchCount(dataList.size());
				response.setResMsg("Fetched successfully");
				response.setStatus(ProjectConstants.SUCCESS_RESPONSE);

		} catch (Exception e) {
			e.printStackTrace();
			response.setResMsg(e.getMessage());
			response.setStatus(ProjectConstants.FAILURE_RESPONSE);
		}
		return response;
	}
	/**
	 * For getting the count of the Failure API Calls.
	 *
	 */
	@GetMapping("/getFailedApisCount")
	public ServiceResponse getFailedApisCount(@RequestParam String collectionName) {
		ServiceResponse response = new ServiceResponse();
		try {
			 
				MongoClient client = MongoClients.create();
				List<Bson> pipeline = new ArrayList<Bson>();
				//Performing Aggregation Pipeline with staging.  
				Document projectSpec =new Document("$match",new Document("response.status", "Failed")); 
				pipeline.add(projectSpec);	
				Document countSpec = new Document("$count", "Failed-Calls");
				pipeline.add(countSpec);	
				List<Document> dataList = client.getDatabase(defaultDbName).getCollection(collectionName).aggregate(pipeline).into(new ArrayList<Document>());
				client.close();
				response.setResObject(dataList);
	            response.setSearchCount(dataList.size());
				response.setResMsg("Fetched successfully");
				response.setStatus(ProjectConstants.SUCCESS_RESPONSE);

		} catch (Exception e) {
			e.printStackTrace();
			response.setResMsg(e.getMessage());
			response.setStatus(ProjectConstants.FAILURE_RESPONSE);
		}
		return response;
	}
	/**
	 * For getting the count of the Successful API Calls.
	 *
	 */
	@GetMapping("/getSuccessApisCount")
	public ServiceResponse getSuccessApisCount(@RequestParam String collectionName) {
		ServiceResponse response = new ServiceResponse();
		try {
			 
				MongoClient client = MongoClients.create();
				List<Bson> pipeline = new ArrayList<Bson>();
				//Performing Aggregation Pipeline with staging.  
				Document projectSpec =new Document("$match",new Document("response.status", "Success")); 
				pipeline.add(projectSpec);	
				Document countSpec = new Document("$count", "Successful-Calls");
				pipeline.add(countSpec);
				List<Document> dataList = client.getDatabase(defaultDbName).getCollection(collectionName).aggregate(pipeline).into(new ArrayList<Document>());
				client.close();
				response.setResObject(dataList);
	            response.setSearchCount(dataList.size());
				response.setResMsg("Fetched successfully");
				response.setStatus(ProjectConstants.SUCCESS_RESPONSE);

		} catch (Exception e) {
			e.printStackTrace();
			response.setResMsg(e.getMessage());
			response.setStatus(ProjectConstants.FAILURE_RESPONSE);
		}
		return response;
	}
	
	/**
	 * For getting the count of the all type of  API Calls.
	 *
	 */
	@GetMapping("/getApisCount")
	public ServiceResponse getApisCount(@RequestParam String collectionName) {
		ServiceResponse response = new ServiceResponse();
		try {
			 
				MongoClient client = MongoClients.create();
				List<Bson> pipeline = new ArrayList<Bson>();
				//Performing Aggregation Pipeline with staging.  
				Document projectSpec =new Document("$project",new Document("response.status", 1L).append("apiUrl", 1L));
				pipeline.add(projectSpec);	
				Document grpSpec =  new Document("$group", 
					    new Document("_id", "$response.status")
			            .append("statusCount", 
			            new Document("$count", 
			            new Document()))); 
				pipeline.add(grpSpec);
				Document sortSpec = new Document("$sort",new Document("statusCount", -1L));
				pipeline.add(sortSpec);
				List<Document> dataList = client.getDatabase(defaultDbName).getCollection(collectionName).aggregate(pipeline).into(new ArrayList<Document>());
				client.close();
				response.setResObject(dataList);
	            response.setSearchCount(dataList.size());
				response.setResMsg("Fetched successfully");
				response.setStatus(ProjectConstants.SUCCESS_RESPONSE);

		} catch (Exception e) {
			e.printStackTrace();
			response.setResMsg(e.getMessage());
			response.setStatus(ProjectConstants.FAILURE_RESPONSE);
		}
		return response;
	}
	
}
