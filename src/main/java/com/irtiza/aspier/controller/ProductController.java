package com.irtiza.aspier.controller;

import com.irtiza.aspier.entity.Customer;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    @NullMarked
    public ResponseEntity<Customer> getProducts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            Customer customer = (Customer) authentication.getPrincipal();
            return ResponseEntity.ok(customer);
        }
        throw new IllegalArgumentException("Not authenticated");
    }
}
