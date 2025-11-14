package com.irtiza.aspier.repository;

import com.irtiza.aspier.entity.Order;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;

@NullMarked
public interface OrderRepository extends JpaRepository<Order, Long> {
}
