package com.calculator.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calculator.CalculationsPK;
import com.calculator.entity.Calculations;
import com.calculator.exception.ValidationException;
import com.calculator.repository.CalculationsRepository;
import com.calculator.util.Operations;
import com.calculator.validator.NumberValidator;
import com.calculator.validator.OperationValidator;



@Service
public class CalculatorService implements ICalculatorService {
	
	@Autowired
	private CalculationsRepository calculationsRepository;
	
	private Map<Operations, BiFunction<BigDecimal, BigDecimal, BigDecimal>> calculatorMap;
	
	@PostConstruct
	public void postConstruct() {
		calculatorMap = new HashMap<Operations, BiFunction<BigDecimal, BigDecimal, BigDecimal>>();
		calculatorMap.put(Operations.ADD, (x, y) -> x.add(y));
		calculatorMap.put(Operations.SUBTRACT, (x, y) -> x.subtract(y));
		calculatorMap.put(Operations.MULTIPLY, (x, y) -> x.multiply(y));
		calculatorMap.put(Operations.DIVIDE, (x, y) -> x.divide(y, 5, RoundingMode.HALF_UP));
	}

	@Override
	@Transactional
	public String performOperation(String operation, String firstNumber, String secondNumber) {
		try {
			if(new OperationValidator().validate(operation) && new NumberValidator().validate(firstNumber) && new NumberValidator().validate(secondNumber)) {
				Optional<Calculations> calculations = calculationsRepository.findById(new CalculationsPK(operation, new BigDecimal(firstNumber), new BigDecimal(secondNumber)));
				if(!calculations.isPresent()) {
					return performCalculation(operation, new BigDecimal(firstNumber), new BigDecimal(secondNumber));
				} else {
					return calculations.get().getResult().toString();
				}
			} 
		} catch (ValidationException ve) {
			return ve.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return "Unknown exception while processing request "+ e.getMessage();
		}
		
		return "Invalid Response";
	}

	private String performCalculation(String operation, BigDecimal firstNumber, BigDecimal secondNumber) {
		BigDecimal result = calculatorMap.get(Operations.valueOf(StringUtils.upperCase(operation))).apply(firstNumber, secondNumber);
		calculationsRepository.save(new Calculations().setId(new CalculationsPK(operation, firstNumber, secondNumber)).setResult(result).setCalculatedOn(LocalDateTime.now()));
		return result.toString();
	}

}
