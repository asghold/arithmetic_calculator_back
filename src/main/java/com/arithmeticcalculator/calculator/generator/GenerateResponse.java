package com.arithmeticcalculator.calculator.generator;

import lombok.Data;

@Data
public class GenerateResponse {

    private String jsonrpc;
    private Integer id;
    private Result result;

}
