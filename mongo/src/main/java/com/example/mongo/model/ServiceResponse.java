/**
 * 
 */
package com.example.mongo.model;

import lombok.Data;

/**
 * @author ChandanSarkar
 *
 */
@Data
public class ServiceResponse {

	private Object resObject;
	private int status;
	private String resMsg;
	private int searchCount;
}
