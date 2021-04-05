package com.calculator.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.calculator.CalculationsPK;
import com.calculator.entity.Calculations;
import com.calculator.repository.CalculationsRepository;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorServiceTest {

	@InjectMocks
	private CalculatorService calculatorService;

	@Mock
	private CalculationsRepository calculationsRepository;

	private Calculations calculations;

	@Before
	public void setUp() {
		calculations = mock(Calculations.class);
		calculatorService.postConstruct();
		when(calculationsRepository.findById(any(CalculationsPK.class))).thenReturn(Optional.empty());
	}

	@Test
	public void testPerformOperationWithInsert() {
		assertEquals("7.25000", calculatorService.performOperation("Divide", "14.5", "2"));
		verify(calculationsRepository, times(1)).save(any(Calculations.class));
	}
	
	@Test
	public void testPerformOperationWithOutInsert() {
		when(calculations.getResult()).thenReturn(new BigDecimal("7.25"));
		when(calculationsRepository.findById(any(CalculationsPK.class))).thenReturn(Optional.of(calculations));
		assertEquals(calculations.getResult().toString(), calculatorService.performOperation("Divide", "14.5", "2"));
		verify(calculationsRepository, times(0)).save(any(Calculations.class));
	}
	
	@Test
	public void testOperationNotPresent() {
		assertEquals("Operation not passed", calculatorService.performOperation(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY));
		verify(calculationsRepository, times(0)).findById(any(CalculationsPK.class));
		verify(calculationsRepository, times(0)).save(any(Calculations.class));
	}
	
	@Test
	public void testOperationInvalid() {
		assertTrue(StringUtils.contains(calculatorService.performOperation("Test", StringUtils.EMPTY, StringUtils.EMPTY), "is an invalid operation"));
		verify(calculationsRepository, times(0)).findById(any(CalculationsPK.class));
		verify(calculationsRepository, times(0)).save(any(Calculations.class));
	}
	
	@Test
	public void testNumberNotPresent() {
		assertEquals("Either number is not passed", calculatorService.performOperation("Add", StringUtils.EMPTY, StringUtils.EMPTY));
		verify(calculationsRepository, times(0)).findById(any(CalculationsPK.class));
		verify(calculationsRepository, times(0)).save(any(Calculations.class));
	}
	
	@Test
	public void testNumberIsInvalid() {
		assertTrue(StringUtils.contains(calculatorService.performOperation("Add", "2a", StringUtils.EMPTY), "is an invalid number"));
		verify(calculationsRepository, times(0)).findById(any(CalculationsPK.class));
		verify(calculationsRepository, times(0)).save(any(Calculations.class));
	}
	
	@Test
	public void testException() {
		assertTrue(StringUtils.contains(calculatorService.performOperation("Divide", "2", "0"), "Unknown exception while processing request"));
		verify(calculationsRepository, times(0)).save(any(Calculations.class));
	}

}
