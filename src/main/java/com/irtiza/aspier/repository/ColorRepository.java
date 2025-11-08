package com.irtiza.aspier.repository;

import com.irtiza.aspier.entity.ProductColor;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@NullMarked
public interface ColorRepository extends JpaRepository<ProductColor, Long> {
    Optional<ProductColor> findByColor(String name);
}
