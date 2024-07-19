package com.arithmeticcalculator.calculator.generator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenerateRequestData {

    private Integer id;
    private String jsonrpc;
    private String method;
    private GenerateStringsParams params; 

}
