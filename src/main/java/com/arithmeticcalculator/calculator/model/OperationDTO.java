package com.arithmeticcalculator.calculator.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OperationDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;

	private Double cost;

	private String type;

	private List<RecordDTO> records;


}
