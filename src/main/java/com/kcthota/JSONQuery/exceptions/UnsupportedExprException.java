/**
 * 
 */
package com.kcthota.JSONQuery.exceptions;

/**
 * @author Krishna Chaitanya Thota
 * Apr 23, 2015 10:57:41 PM
 */
@SuppressWarnings("serial")
public class UnsupportedExprException extends RuntimeException {

	public UnsupportedExprException() {
		super();
	}

	public UnsupportedExprException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UnsupportedExprException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnsupportedExprException(String message) {
		super(message);
	}

	public UnsupportedExprException(Throwable cause) {
		super(cause);
	}

}
