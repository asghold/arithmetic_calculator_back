package com.arithmeticcalculator.calculator.controller;

import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arithmeticcalculator.calculator.exception.AppException;
import com.arithmeticcalculator.calculator.model.RoleName;
import com.arithmeticcalculator.calculator.model.UserDTO;
import com.arithmeticcalculator.calculator.payload.ApiResponse;
import com.arithmeticcalculator.calculator.payload.JwtAuthenticationResponse;
import com.arithmeticcalculator.calculator.payload.LoginRequest;
import com.arithmeticcalculator.calculator.payload.SingUpRequest;
import com.arithmeticcalculator.calculator.security.JwtTokenProvider;
import com.arithmeticcalculator.calculator.service.RoleService;
import com.arithmeticcalculator.calculator.service.UserService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwtTokenProvider.generateToken(authentication)));
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SingUpRequest singUpRequest) {

        if(userService.existsByUsername(singUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!!"), HttpStatus.BAD_REQUEST);
        }
        
        UserDTO userSaved = userService.saveUser(new UserDTO("",passwordEncoder.encode(singUpRequest.getPassword()),true,singUpRequest.getUsername(), null, Collections.singleton(roleService.findByName(RoleName.ROLE_USER).orElseThrow(()-> new AppException("User Role not set.")))));

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}").buildAndExpand(userSaved.getUsername()).toUri()).body(new ApiResponse(true, "User registered successfully"));
        
        
    }
    

}
