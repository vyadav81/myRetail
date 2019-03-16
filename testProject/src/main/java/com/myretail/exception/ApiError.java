package com.myretail.exception;

import java.util.Date;

/**
 * Api Error business object used to return error codes.
 * 
 * @author vyadav
 *
 */
public class ApiError {

	/** Timestamp of occurence of exception */
	private Date timestamp;
	/** exception message */
	private String message;

	public ApiError(Date timestamp, String message) {
		super();
		this.timestamp = timestamp;
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

}
