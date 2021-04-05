package com.calculator.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.calculator.exception.ValidationException;

public class NumberValidator implements IValidator {

	@Override
	public boolean validate(String number) throws ValidationException {
		if (StringUtils.isEmpty(number)) {
			throw new ValidationException("Either number is not passed");
		} else if (!NumberUtils.isCreatable(number)) {
			throw new ValidationException(number + " is an invalid number");
		}
		return true;
	}

}
