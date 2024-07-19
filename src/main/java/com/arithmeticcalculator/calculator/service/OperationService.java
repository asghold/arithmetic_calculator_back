package com.arithmeticcalculator.calculator.service;

import java.util.List;

import com.arithmeticcalculator.calculator.model.DataGenericDTO;
import com.arithmeticcalculator.calculator.model.OperationDTO;
import com.arithmeticcalculator.calculator.model.RecordDTO;
import com.arithmeticcalculator.calculator.model.UserDTO;

public interface OperationService {

    List<OperationDTO> getOperations();

    RecordDTO operation(UserDTO userDTO, Long id, DataGenericDTO data) throws Exception;

    RecordDTO getLastRecord(UserDTO userDTO);

}
