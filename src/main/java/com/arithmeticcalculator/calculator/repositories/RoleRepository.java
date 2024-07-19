package com.arithmeticcalculator.calculator.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arithmeticcalculator.calculator.entities.Role;
import com.arithmeticcalculator.calculator.model.RoleName;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);

    
} 
