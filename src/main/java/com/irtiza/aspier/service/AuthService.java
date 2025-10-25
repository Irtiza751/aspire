package com.irtiza.aspier.service;

import com.irtiza.aspier.dto.SigninResponse;
import com.irtiza.aspier.dto.SignupResponse;
import com.irtiza.aspier.entity.Customer;
import com.irtiza.aspier.entity.Role;
import com.irtiza.aspier.repository.CustomerRepository;
import com.irtiza.aspier.request.SigninRequest;
import com.irtiza.aspier.request.SignupRequest;
import com.irtiza.aspier.security.JwtServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtServiceImpl;

    public SignupResponse register(SignupRequest signupRequest) {
        Customer customer = Customer.builder()
                .username(signupRequest.getUsername())
                .email(signupRequest.getEmail())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .role(Role.USER)
                .build();
        Customer savedCustomer = customerRepository.save(customer);
        return SignupResponse.builder()
                .id(savedCustomer.getId())
                .username(savedCustomer.getUsername())
                .email(savedCustomer.getEmail())
                .role(savedCustomer.getRole())
                .build();
    }

    public List<SignupResponse> getCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> SignupResponse.builder()
                        .id(customer.getId())
                        .username(customer.getUsername())
                        .email(customer.getEmail())
                        .role(customer.getRole())
                        .build())
                .toList();
    }

    public SigninResponse loginUser(SigninRequest signinRequest) {
        Customer customer = customerRepository.findByEmail(signinRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email: " + signinRequest.getEmail()));

        Map<String, String> claims = new Hashtable<>();
        claims.put("userId", customer.getId().toString());
        claims.put("userRole", customer.getRole().name());

        String jwt = jwtServiceImpl.generateToken(claims, customer);
        return new SigninResponse(jwt);
    }
}
