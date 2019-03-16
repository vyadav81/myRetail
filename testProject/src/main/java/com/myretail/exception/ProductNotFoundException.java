package com.myretail.exception;

/**
 * This is custom exception when we are unable to retrieve product detail form
 * third party
 * 
 * @author vyadav
 *
 */
@SuppressWarnings("serial")
public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException(String message) {
		super(message);
	}

}
