package com.arithmeticcalculator.calculator.generator;

import lombok.Data;

@Data
public class Result {
    private Long bitsUsed;
    private long bitsLeft;
    private int requestsLeft;
    private int advisoryDelay;
    private RandomData random;
}
