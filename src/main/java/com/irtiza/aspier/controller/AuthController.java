package com.irtiza.aspier.controller;

import com.irtiza.aspier.dto.SigninResponse;
import com.irtiza.aspier.dto.SignupResponse;
import com.irtiza.aspier.request.SigninRequest;
import com.irtiza.aspier.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.irtiza.aspier.request.SignupRequest;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, World");
    }

    @PostMapping("/signup")
    @NullMarked
    public ResponseEntity<SignupResponse> registerUser(@RequestBody SignupRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.register(request));
    }

    @GetMapping("/users")
    @NullMarked
    public ResponseEntity<List<SignupResponse>> getCustomers() {
        return ResponseEntity.ok(authService.getCustomers());
    }

    @PostMapping("/signin")
    @NullMarked
    public ResponseEntity<SigninResponse> loginUser(@RequestBody SigninRequest signinRequest) {
        return new ResponseEntity<>(authService.loginUser(signinRequest), HttpStatus.CREATED);
    }
}
