package com.arithmeticcalculator.calculator.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RecordDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;
    private Double amount;
	private Date date;
	private String operationResponse;
	private Double userBalance;
	private OperationDTO operation;
	private UserDTO user;

}
