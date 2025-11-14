package com.irtiza.aspier.repository;

import com.irtiza.aspier.entity.OrderItem;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@NullMarked
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Page<OrderItem> findByOrderUserId(Long id, Pageable pageable);
}
