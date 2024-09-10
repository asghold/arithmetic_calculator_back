package com.arithmeticcalculator.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arithmeticcalculator.calculator.model.UserDTO;
import com.arithmeticcalculator.calculator.security.CurrentUser;
import com.arithmeticcalculator.calculator.security.UserDetailsImpl;
import com.arithmeticcalculator.calculator.service.RecordService;


@RestController
@RequestMapping("/api/records")
public class RecordsController {

    @Autowired
    private RecordService recordService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getAllRecords(@CurrentUser UserDetailsImpl currentUser) {
        try {
            
            return new ResponseEntity<>(recordService.getAll(new UserDTO(currentUser.getId(),currentUser.getPassword(),true,currentUser.getUsername(), null, null)), HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> deleteRecord(@CurrentUser UserDetailsImpl currentUser, @PathVariable String id){
        recordService.deleteRecord(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    

}
