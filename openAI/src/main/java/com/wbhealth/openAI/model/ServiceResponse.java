/**
 * 
 */
package com.wbhealth.openAI.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DA217MT
 *
 */
@Data
@NoArgsConstructor
public class ServiceResponse {
	private static final long serialVersionUID = 2331307404151448988L;
	private String status;
	private String responseMessage;
	private int searchCount;
	private Object resObject;
	private int statusCode;
}
