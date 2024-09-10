package com.arithmeticcalculator.calculator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arithmeticcalculator.calculator.model.DataGenericDTO;
import com.arithmeticcalculator.calculator.model.OperationDTO;
import com.arithmeticcalculator.calculator.model.RecordDTO;
import com.arithmeticcalculator.calculator.model.UserDTO;
import com.arithmeticcalculator.calculator.security.CurrentUser;
import com.arithmeticcalculator.calculator.security.UserDetailsImpl;
import com.arithmeticcalculator.calculator.service.OperationService;




@RestController
@RequestMapping("/api/calculator")
public class ArithmeticCalculatorController {

    @Autowired
    private OperationService operationService;


    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<OperationDTO> getOperations(@CurrentUser UserDetailsImpl currenUser) {
        
        return operationService.getOperations();
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> generateOperation(@CurrentUser UserDetailsImpl currentUser, @PathVariable Long id, @RequestBody DataGenericDTO data) {
        try {
            return new ResponseEntity<RecordDTO>(operationService.operation(new UserDTO(currentUser.getId(),currentUser.getPassword(),true,currentUser.getUsername(), null, null), id, data), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } 
        
    }
    
    @GetMapping("/balance")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getBalance(@CurrentUser UserDetailsImpl currentUser) {
        try{
            return new ResponseEntity<RecordDTO>(operationService.getLastRecord(new UserDTO(currentUser.getId(),currentUser.getPassword(),true,currentUser.getUsername(), null, null)), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
    }
    


    

}
