package com.irtiza.aspier.service;

import com.irtiza.aspier.dto.ProductResponse;
import com.irtiza.aspier.entity.User;
import com.irtiza.aspier.entity.Product;
import com.irtiza.aspier.repository.UserRepository;
import com.irtiza.aspier.repository.ProductRepository;
import com.irtiza.aspier.request.ProductRequest;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository customerRepository;

    public ProductService(ProductRepository productRepository, UserRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public ProductResponse create(ProductRequest productRequest) {
        User user = getPrincipal(); // authenticated user object
        User savedUser = customerRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Product product = Product.builder()
                .name(productRequest.getName())
                .slug(productRequest.getSlug())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .creator(savedUser)
                .build();

        Product savedProduct = productRepository.save(product);
        return new ProductResponse(
                savedProduct.getId(),
                savedProduct.getSlug(),
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getPrice(),
                savedProduct.getCreatedAt(),
                savedProduct.getUpdatedAt()
        );
    }

    private User getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("User is not authorize");
        }
        return (User) authentication.getPrincipal();
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getSlug(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getCreatedAt(),
                        product.getUpdatedAt()
                )).toList();
    }
}
