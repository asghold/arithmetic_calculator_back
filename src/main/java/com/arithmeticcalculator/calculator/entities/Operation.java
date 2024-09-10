package com.arithmeticcalculator.calculator.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Operation")
public class Operation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

	private Double cost;

	private String type;

	private Boolean valid;

	//bi-directional many-to-one association to Record
	@OneToMany(mappedBy="operation")
	private List<RecordsOperation> records;

	public Operation() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<RecordsOperation> getRecords() {
		return this.records;
	}

	public void setRecords(List<RecordsOperation> records) {
		this.records = records;
	}

	public RecordsOperation addRecord(RecordsOperation record) {
		getRecords().add(record);
		record.setOperation(this);

		return record;
	}

	public RecordsOperation removeRecord(RecordsOperation record) {
		getRecords().remove(record);
		record.setOperation(null);

		return record;
	}


	public Boolean isValid() {
		return this.valid;
	}

	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}


}