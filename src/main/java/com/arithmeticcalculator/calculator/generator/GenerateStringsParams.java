package com.arithmeticcalculator.calculator.generator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenerateStringsParams {
    
    private String apiKey;
    private Integer n;
    private Integer length;
    private String characters;
    private boolean replacement;

}
