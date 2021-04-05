package com.calculator.validator;

import com.calculator.exception.ValidationException;

public interface IValidator {

	boolean validate(String arg) throws ValidationException;

}
