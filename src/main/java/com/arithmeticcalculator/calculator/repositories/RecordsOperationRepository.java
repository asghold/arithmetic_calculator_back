package com.arithmeticcalculator.calculator.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arithmeticcalculator.calculator.entities.Operation;
import com.arithmeticcalculator.calculator.entities.RecordsOperation;
import com.arithmeticcalculator.calculator.entities.User;


public interface RecordsOperationRepository extends JpaRepository<RecordsOperation, String>{

    //@Query("Select r from Record r where r.")
    //List<Record> findByUserAndOperation(@Param("idUser") Long idUser, @Param("idOperation") Long idOperation);
    List<RecordsOperation> findByUserAndOperation(User user, Operation operation);

    List<RecordsOperation> findByUser(User user);

    
} 
