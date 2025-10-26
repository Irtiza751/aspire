package com.irtiza.aspier.repository;

import com.irtiza.aspier.entity.Product;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@NullMarked
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
