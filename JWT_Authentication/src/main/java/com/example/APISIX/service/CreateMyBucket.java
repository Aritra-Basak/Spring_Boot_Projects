/**
 * 
 */
package com.example.APISIX.service;

import java.time.Duration;
import java.util.Date;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

/**
 * @author CQ927DW
 *
 */
@Service
public class CreateMyBucket {

	 private final LinkedHashMap<String, Bucket> userMap = new LinkedHashMap<>();

	    public Bucket resolveBucket(String userId) {
	    	//mapping each created bucket for API calls with respect to the userId. It's getting mapped only if the userID is absent in the HashMap.
	        return userMap.computeIfAbsent(userId, this::newBucket);
	    }

	    private Bucket newBucket(String userId) {
	    	
	    	int count =1;
                Date d = new Date();
                //If the current time is before 5:00pm then 5 request will be allowed per minute else 3 calls
	        	if(d.getHours()<=17)
	        	{
	        	 count =2;	
	        	}
	        	//Creating a bucket to allow API's 1/2 requests per minute. In other words, the API rejects a request if it's already received 1/2 requests in a time window of 1 minute
	            Bandwidth limit = Bandwidth.classic(count, Refill.greedy(count, Duration.ofMinutes(1)));//similarly we can use Duration.ofHours(1)
	             return Bucket4j.builder().addLimit(limit).build();
	    }
	
}
