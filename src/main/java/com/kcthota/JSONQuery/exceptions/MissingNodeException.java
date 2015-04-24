/**
 * 
 */
package com.kcthota.JSONQuery.exceptions;

/**
 * @author Krishna Chaitanya Thota
 * Apr 23, 2015 7:22:28 PM
 */
@SuppressWarnings("serial")
public class MissingNodeException extends RuntimeException {

	public MissingNodeException() {
		super();
		
	}

	public MissingNodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public MissingNodeException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public MissingNodeException(String message) {
		super(message);
		
	}

	public MissingNodeException(Throwable cause) {
		super(cause);
	}
	
}
