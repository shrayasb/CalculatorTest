package com.calculator;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class CalculationsPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6615065224978399364L;

	@NotNull
	@Size(max = 10)
	@Column(name = "operation")
	private String operation;

	@NotNull
	@Column(name = "first_number")
	private BigDecimal firstNumber;

	@NotNull
	@Column(name = "second_number")
	private BigDecimal secondNumber;
	
	public CalculationsPK() {
		super();
	}

	public CalculationsPK(String operation, BigDecimal firstNumber, BigDecimal secondNumber) {
		super();
		this.operation = operation;
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
	}

	public String getOperation() {
		return operation;
	}

	public BigDecimal getFirstNumber() {
		return firstNumber;
	}

	public BigDecimal getSecondNumber() {
		return secondNumber;
	}
}
