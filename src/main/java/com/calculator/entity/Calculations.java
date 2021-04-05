package com.calculator.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.calculator.CalculationsPK;

@Entity
@Table(name = "calculations")
public class Calculations implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8532356094908119646L;

	@EmbeddedId
	private CalculationsPK id;

	@NotNull
	@Column(name = "result")
	private BigDecimal result;

	@NotNull
	@Column(name = "calculated_on")
	private LocalDateTime calculatedOn;

	public CalculationsPK getId() {
		return id;
	}

	public Calculations setId(CalculationsPK id) {
		this.id = id;
		return this;
	}

	public BigDecimal getResult() {
		return result;
	}

	public Calculations setResult(BigDecimal result) {
		this.result = result;
		return this;
	}

	public LocalDateTime getCalculatedOn() {
		return calculatedOn;
	}

	public Calculations setCalculatedOn(LocalDateTime calculatedOn) {
		this.calculatedOn = calculatedOn;
		return this;
	}

}
