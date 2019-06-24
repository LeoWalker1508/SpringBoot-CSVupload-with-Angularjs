/**
 * 
 */
package com.everythingisdata.warehouse.exception;

import org.springframework.http.HttpStatus;

/**
 * @author everythingisdata
 *
 */
public class ApiException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2392828495640368531L;
	private final HttpStatus status;
	private final String message;

	public ApiException(HttpStatus status, String message, Throwable ex) {
		super(ex);
		this.status = status;
		this.message = message;
	}

	 
	public HttpStatus getStatus() {
		return status;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
