package com.irtiza.aspier.service;

import com.irtiza.aspier.common.AuthUser;
import com.irtiza.aspier.dto.OrderResponse;
import com.irtiza.aspier.dto.SuccessResponse;
import com.irtiza.aspier.entity.*;
import com.irtiza.aspier.repository.OrderItemRepository;
import com.irtiza.aspier.repository.OrderRepository;
import com.irtiza.aspier.repository.ProductRepository;
import com.irtiza.aspier.repository.UserRepository;
import com.irtiza.aspier.request.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public SuccessResponse createOrder(CreateOrderRequest createOrderRequest) {
        User user = AuthUser.getPrincipal(); // get the authenticated user info
        // query saved user info
        User savedUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        // find the product by product id
        Product product = productRepository.findById(createOrderRequest.getProductId())
                        .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        // create a new order entry
        Order order = Order.builder()
                .status(OrderStatus.PENDING)
                .user(savedUser)
                .build();
        // persist and flush the user
        Order savedOrder = orderRepository.save(order);
        // create a new order item record
        OrderItem orderItem = OrderItem.builder()
                .product(product)
                .order(order)
                .price(product.getPrice() * createOrderRequest.getQuantity())
                .quantity(createOrderRequest.getQuantity())
                .build();
        // save the new order item in the order item table
        savedOrder.addOrderItems(orderItem);
        orderRepository.save(order);

        return new SuccessResponse("Order created successfully", HttpStatus.CREATED.ordinal());
    }

    public List<OrderResponse> getOrders() {
        User user = AuthUser.getPrincipal();
        List<OrderItem> orderItems = orderItemRepository.findByOrderUserId(user.getId());

        return orderItems.stream()
                .map(orderItem -> OrderResponse.builder()
                        .id(orderItem.getOrder().getId())
                        .createdAt(orderItem.getOrder().getCreatedAt())
                        .updatedAt(orderItem.getOrder().getUpdatedAt())
                        .userId(orderItem.getOrder().getUser().getId())
                        .status(orderItem.getOrder().getStatus())
                        .product(orderItem.getProduct().getName())
                        .quantity(orderItem.getQuantity())
                        .price(orderItem.getPrice())
                        .build())
                .toList();
    }
}
