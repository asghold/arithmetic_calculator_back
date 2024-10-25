package com.arithmeticcalculator.calculator.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GenerateStringsServiceTest {

    @Autowired
    private GenerateStringsService generateStringsService;

    @Test
    void generateStringsTest() {

        Integer n = 3;
        Integer length = 20;

        List<String> listStrings;
        listStrings = generateStringsService.generateStrings(n, length);

        assertNotNull(listStrings);
        assertEquals(3, listStrings.size());
    }
}