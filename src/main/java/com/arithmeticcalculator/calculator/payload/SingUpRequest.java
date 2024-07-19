package com.arithmeticcalculator.calculator.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SingUpRequest {
    
    @NotBlank
    @Size(min = 3, max = 100)
    @Email
    private String username;

    @NotBlank
    @Size(min = 6, max = 60)
    private String password;


}
