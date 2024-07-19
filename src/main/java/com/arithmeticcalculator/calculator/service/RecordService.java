package com.arithmeticcalculator.calculator.service;

import java.util.List;

import com.arithmeticcalculator.calculator.model.RecordDTO;
import com.arithmeticcalculator.calculator.model.UserDTO;

public interface RecordService {

    List<RecordDTO> getAll(UserDTO userDTO) throws Exception;

    void deleteRecord(String id);

}
