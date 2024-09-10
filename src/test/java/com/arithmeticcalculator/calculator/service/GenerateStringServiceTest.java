package com.arithmeticcalculator.calculator.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GenerateStringServiceTest {

    @Autowired
    private GenerateStringsService generateStringsService;
    
    @Test
    public void generateStringsTest(){

        Integer n = 3;
        Integer length = 20;

        List<String> listStrings = generateStringsService.generateStrings(n, length);

        assertEquals(3, listStrings.size());
    }

}
