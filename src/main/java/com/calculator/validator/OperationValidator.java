package com.calculator.validator;

import static com.calculator.util.Operations.ADD;
import static com.calculator.util.Operations.MULTIPLY;
import static com.calculator.util.Operations.SUBTRACT;
import static com.calculator.util.Operations.DIVIDE;

import org.apache.commons.lang3.StringUtils;

import com.calculator.exception.ValidationException;


public class OperationValidator implements IValidator {

	@Override
	public boolean validate(String operation) throws ValidationException {
		if (StringUtils.isEmpty(operation)) {
			throw new ValidationException("Operation not passed");
		} else if (!StringUtils.equalsAnyIgnoreCase(operation, ADD.name(), SUBTRACT.name(), DIVIDE.name(), MULTIPLY.name())) {
			throw new ValidationException(operation + " is an invalid operation");
		}
		return true;
	}

}
