package com.arithmeticcalculator.calculator.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="USER")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="id")
	private String id;

	@Column(name="password")
	private String password;

	@Column(name="status")
	private Boolean status;

	@Column(name="username")
	private String username;

	//bi-directional many-to-one association to Record
	@OneToMany(mappedBy="user")
	private List<RecordsOperation> records;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name= "USER_ROLE",
		joinColumns = @JoinColumn(name="user_id"),
		inverseJoinColumns = @JoinColumn(name= "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<RecordsOperation> getRecords() {
		return this.records;
	}

	public void setRecords(List<RecordsOperation> records) {
		this.records = records;
	}

	public RecordsOperation addRecord(RecordsOperation record) {
		getRecords().add(record);
		record.setUser(this);

		return record;
	}

	public RecordsOperation removeRecord(RecordsOperation record) {
		getRecords().remove(record);
		record.setUser(null);

		return record;
	}
	
	public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
