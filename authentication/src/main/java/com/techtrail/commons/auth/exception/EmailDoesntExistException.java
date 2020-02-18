package com.techtrail.commons.auth.exception;

public class EmailDoesntExistException  extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmailDoesntExistException(final String msg) {
        super(msg);
    }

}
