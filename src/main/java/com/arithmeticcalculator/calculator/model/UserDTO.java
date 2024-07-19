package com.arithmeticcalculator.calculator.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
	private String password;

	private Boolean status;

	private String username;

	private List<RecordDTO> records;

	private Set<RoleDTO> roles = new HashSet<>();


}
