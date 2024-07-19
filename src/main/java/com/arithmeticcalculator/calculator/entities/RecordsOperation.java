package com.arithmeticcalculator.calculator.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Record")
public class RecordsOperation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private Double amount;

	private Date date;

	@Column(name="operation_response")
	private String operationResponse;

	@Column(name="user_balance")
	private Double userBalance;

	//bi-directional many-to-one association to Operation
	@ManyToOne
    @JoinColumn(name="operation_id")
	private Operation operation;

	//bi-directional many-to-one association to User
	@ManyToOne
    @JoinColumn(name="user_id")
	private User user;

	public RecordsOperation() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getOperationResponse() {
		return this.operationResponse;
	}

	public void setOperationResponse(String operationResponse) {
		this.operationResponse = operationResponse;
	}

	public Double getUserBalance() {
		return this.userBalance;
	}

	public void setUserBalance(Double userBalance) {
		this.userBalance = userBalance;
	}

	public Operation getOperation() {
		return this.operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
