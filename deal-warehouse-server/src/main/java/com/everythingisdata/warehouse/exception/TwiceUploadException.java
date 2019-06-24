package com.everythingisdata.warehouse.exception;

public class TwiceUploadException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TwiceUploadException(String msg) {
		super(msg);
	}

}
