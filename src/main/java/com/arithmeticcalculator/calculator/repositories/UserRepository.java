package com.arithmeticcalculator.calculator.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arithmeticcalculator.calculator.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

}
