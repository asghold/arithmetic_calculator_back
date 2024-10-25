package com.arithmeticcalculator.calculator.service;

import com.arithmeticcalculator.calculator.model.DataGenericDTO;
import com.arithmeticcalculator.calculator.model.RecordDTO;
import com.arithmeticcalculator.calculator.model.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RecordServiceTest {

    private Long id;
    private UserDTO userDTO;
    private DataGenericDTO dataGenericDTO;

    @Autowired
    private RecordService recordService;

    @Autowired
    private OperationService operationService;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO("a235c04c-f3c1-4743-a002-7bbbc9661872", null, true, "user1@mail.com", null, null);
        id = 3L;
        dataGenericDTO = new DataGenericDTO();
        dataGenericDTO.setNumbers(Arrays.asList(24.0, 3.0));
    }

    @Test
    void getAll() throws Exception {

        List<RecordDTO> records = recordService.getAll(userDTO);

        assertNotNull(records);
    }

    @Test
    void deleteRecord() throws Exception {

        RecordDTO recordDTO = operationService.operation(userDTO, id, dataGenericDTO);
        List<RecordDTO> recordDTOS = recordService.getAll(userDTO);

        assertNotNull(recordDTO);
        assertNotNull(recordDTOS);

        recordService.deleteRecord(recordDTO.getId());

        List<RecordDTO> recordsNew = recordService.getAll(userDTO);

        assertNotEquals(recordDTOS, recordsNew);


    }
}