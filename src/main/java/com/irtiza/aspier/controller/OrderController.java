package com.irtiza.aspier.controller;

import com.irtiza.aspier.request.CreateOrderRequest;
import com.irtiza.aspier.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest) {
        return new ResponseEntity<>(orderService.createOrder(createOrderRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getOrders(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(orderService.getOrders(page, size));
    }
}
