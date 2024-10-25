package com.arithmeticcalculator.calculator.service;

import com.arithmeticcalculator.calculator.model.DataGenericDTO;
import com.arithmeticcalculator.calculator.model.OperationDTO;
import com.arithmeticcalculator.calculator.model.RecordDTO;
import com.arithmeticcalculator.calculator.model.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OperationServiceTest {

    @Autowired
    private OperationService operationService;

    @Test
    public void getOperationsTest() {
        List<OperationDTO> operations = Arrays.asList(new OperationDTO(1L, 0.25, "Addition", null), new OperationDTO(2L, 0.25, "Subtraction", null), new OperationDTO(3L, 0.5, "Multiplication", null), new OperationDTO(4L, 0.5, "Division", null), new OperationDTO(5L, 0.75, "Square Root", null), new OperationDTO(6L, 1.25, "Random String", null));

        List<OperationDTO> operationDTOs = operationService.getOperations();
        assertEquals(operations, operationDTOs);
    }

    @Test
    @DisplayName("Addition test case")
    public void operationTest() throws Exception {

        UserDTO userDTO = new UserDTO("a235c04c-f3c1-4743-a002-7bbbc9661872", null, true, "user1@mail.com", null, null);
        Long id = 1L;
        DataGenericDTO dataGenericDTO = new DataGenericDTO();
        dataGenericDTO.setNumbers(Arrays.asList(24.0, 3.0));
        RecordDTO recordDTO = operationService.operation(userDTO, id, dataGenericDTO);
        assertNotNull(recordDTO);

    }

    @Test
    @DisplayName("Subtraction test case")
    public void operationSubtractionTest() throws Exception {

        UserDTO userDTO = new UserDTO("a235c04c-f3c1-4743-a002-7bbbc9661872", null, true, "user1@mail.com", null, null);
        Long id = 2L;
        DataGenericDTO dataGenericDTO = new DataGenericDTO();
        dataGenericDTO.setNumbers(Arrays.asList(24.0, 3.0));
        RecordDTO recordDTO = operationService.operation(userDTO, id, dataGenericDTO);
        assertNotNull(recordDTO);
        assertEquals("21.0", recordDTO.getOperationResponse());

    }

    @Test
    @DisplayName("Multiplication test case")
    public void operationMultiplicationTest() throws Exception {

        UserDTO userDTO = new UserDTO("a235c04c-f3c1-4743-a002-7bbbc9661872", null, true, "user1@mail.com", null, null);
        Long id = 3L;
        DataGenericDTO dataGenericDTO = new DataGenericDTO();
        dataGenericDTO.setNumbers(Arrays.asList(24.0, 3.0));
        RecordDTO recordDTO = operationService.operation(userDTO, id, dataGenericDTO);
        assertNotNull(recordDTO);
        assertEquals("72.0", recordDTO.getOperationResponse());
    }

    @Test
    @DisplayName("Last Record test case")
    public void lastRecordTest() throws Exception {
        UserDTO userDTO = new UserDTO("a235c04c-f3c1-4743-a002-7bbbc9661872", null, true, "user1@mail.com", null, null);

        RecordDTO recordDTO = operationService.getLastRecord(userDTO);

        assertNotNull(recordDTO);
    }
}