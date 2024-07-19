package com.arithmeticcalculator.calculator.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class DataGenericDTO  implements Serializable{

    private static final long serialVersionUID = 1L;

    private List<Double> numbers;

    private String randomString;

    private Integer numberStrings;

    private Integer length;

}
