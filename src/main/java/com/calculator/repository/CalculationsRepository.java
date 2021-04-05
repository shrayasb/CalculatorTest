package com.calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calculator.CalculationsPK;
import com.calculator.entity.Calculations;

@Repository
public interface CalculationsRepository extends JpaRepository<Calculations, CalculationsPK> {

}
