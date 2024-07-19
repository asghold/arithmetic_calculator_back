package com.arithmeticcalculator.calculator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arithmeticcalculator.calculator.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long>{

    

}
