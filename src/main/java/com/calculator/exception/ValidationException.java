package com.calculator.exception;

public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3181453448958748545L;

	public ValidationException(String errorMessage) {
		super(errorMessage);
	}
}