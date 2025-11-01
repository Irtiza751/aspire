package com.irtiza.aspier.service;

import com.irtiza.aspier.dto.SigninResponse;
import com.irtiza.aspier.dto.SignupResponse;
import com.irtiza.aspier.entity.User;
import com.irtiza.aspier.entity.Role;
import com.irtiza.aspier.repository.UserRepository;
import com.irtiza.aspier.request.SigninRequest;
import com.irtiza.aspier.request.SignupRequest;
import com.irtiza.aspier.security.JwtServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtServiceImpl;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public SignupResponse register(SignupRequest signupRequest) {
        User user = User.builder()
                .username(signupRequest.getUsername())
                .email(signupRequest.getEmail())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .roles(Set.of(Role.ROLE_CUSTOMER))
                .build();
        User savedCustomer = customerRepository.save(user);
        return SignupResponse.builder()
                .id(savedCustomer.getId())
                .username(savedCustomer.getUsername())
                .email(savedCustomer.getEmail())
                .roles(savedCustomer.getAuthorities())
                .build();
    }

    public List<SignupResponse> getCustomers() {
        return customerRepository.findAll().stream()
                .map(user -> SignupResponse.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .roles(user.getAuthorities())
                        .build())
                .toList();
    }

    public SigninResponse loginUser(SigninRequest signinRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword())
        );

        if (!authentication.isAuthenticated()) {
            throw new IllegalArgumentException("User is not authenticated");
        }

        User user = customerRepository.findByEmail(signinRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email: " + signinRequest.getEmail()));

        Map<String, String> claims = new Hashtable<>();
        claims.put("userId", user.getId().toString());

        String jwt = jwtServiceImpl.generateToken(claims, user);
        return new SigninResponse(jwt);
    }
}
