package com.calculator.controller;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calculator.service.ICalculatorService;

@RestController
@RequestMapping(path = "/calculator")
public class CalculatorController {

	@Autowired
	private ICalculatorService calculatorService;

	@PostMapping(path = "/")
	public String calculate(@RequestParam(name = "operation", defaultValue = EMPTY) String operation,
			@RequestParam(name = "firstNumber", defaultValue = EMPTY) String firstNumber,
			@RequestParam(name = "secondNumber", defaultValue = EMPTY) String secondNumber) {
		return calculatorService.performOperation(trimToEmpty(operation), trimToEmpty(firstNumber), trimToEmpty(secondNumber));
	}

}
