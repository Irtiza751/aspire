package com.irtiza.aspier.repository;

import com.irtiza.aspier.entity.ProductImage;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@NullMarked
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    Optional<ProductImage> findByUrl(String url);
}
