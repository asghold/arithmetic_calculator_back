package com.arithmeticcalculator.calculator.service;

import java.util.Optional;

import com.arithmeticcalculator.calculator.model.RoleDTO;
import com.arithmeticcalculator.calculator.model.RoleName;

public interface RoleService {

    Optional<RoleDTO> findByName(RoleName name);

}
