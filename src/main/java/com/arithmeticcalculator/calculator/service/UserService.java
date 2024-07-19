package com.arithmeticcalculator.calculator.service;

import java.util.Optional;

import com.arithmeticcalculator.calculator.model.UserDTO;

public interface UserService {

    Optional<UserDTO> findByUsername(String username);

    Boolean existsByUsername(String username);

    UserDTO saveUser(UserDTO userDTO);

}
