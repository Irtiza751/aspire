package com.irtiza.aspier.service;

import com.irtiza.aspier.dto.ProductResponse;
import com.irtiza.aspier.entity.ProductColor;
import com.irtiza.aspier.entity.User;
import com.irtiza.aspier.entity.Product;
import com.irtiza.aspier.repository.UserRepository;
import com.irtiza.aspier.repository.ProductRepository;
import com.irtiza.aspier.request.ColorRequest;
import com.irtiza.aspier.request.ProductRequest;
import jakarta.transaction.Transactional;
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

        Product.ProductBuilder productBuilder = Product.builder()
                .name(productRequest.getName())
                .slug(productRequest.getSlug())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .creator(savedUser);

        if(productRequest.getColors() != null && !productRequest.getColors().isEmpty()) {
           List<ProductColor> colors = productRequest.getColors().stream()
                   .map(color -> new ProductColor(color.getColor()))
                   .toList();
           productBuilder.colors(colors);
        }

        Product savedProduct = productRepository.save(productBuilder.build());
        ProductResponse response = new ProductResponse();

        response.setId(savedProduct.getId());
        response.setSlug(savedProduct.getSlug());
        response.setName(savedProduct.getName());
        response.setDescription(savedProduct.getDescription());
        response.setCreatedAt(savedProduct.getCreatedAt());
        response.setUpdatedAt(savedProduct.getUpdatedAt());

        if(savedProduct.getColors() != null && !savedProduct.getColors().isEmpty()) {
            response.setColors(savedProduct.getColors()
                    .stream()
                    .map(color -> new ColorRequest(color.getColor()))
                    .toList()
            );
        }

        return response;
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
                        product.getColors().stream()
                                .map(color -> new ColorRequest(color.getColor()))
                                .toList(),
                        product.getCreatedAt(),
                        product.getUpdatedAt()
                )).toList();
    }
}
