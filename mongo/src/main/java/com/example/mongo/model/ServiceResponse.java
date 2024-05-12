/**
 * 
 */
package com.example.mongo.model;

import lombok.Data;

/**
 * @author Aritra Basak
 *
 */
@Data
public class ServiceResponse {

	private Object resObject;
	private int status;
	private String resMsg;
	private int searchCount;
}
