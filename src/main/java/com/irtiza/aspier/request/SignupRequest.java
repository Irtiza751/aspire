package com.irtiza.aspier.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SignupRequest {
    @NotEmpty(message = "username is required")
    private String username;

    @NotEmpty(message = "email is required")
    @Email(message = "Please enter valid email")
    private String email;

    @NotEmpty(message = "password is required")
    private String password;
}
