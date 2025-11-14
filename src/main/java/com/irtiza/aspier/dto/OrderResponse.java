package com.irtiza.aspier.dto;

import com.irtiza.aspier.entity.OrderStatus;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class OrderResponse {
    private long id; // order id
    private long userId;
    private OrderStatus status;
    private String product; // name of the product
    private int quantity;
    private float price;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
