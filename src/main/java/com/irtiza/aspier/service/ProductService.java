package com.irtiza.aspier.service;

import com.irtiza.aspier.common.AuthUser;
import com.irtiza.aspier.dto.ProductResponse;
import com.irtiza.aspier.dto.SuccessResponse;
import com.irtiza.aspier.entity.*;
import com.irtiza.aspier.repository.*;
import com.irtiza.aspier.request.ColorRequest;
import com.irtiza.aspier.request.ProductImageRequest;
import com.irtiza.aspier.request.ProductRequest;
import com.irtiza.aspier.request.ProductSizeRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository customerRepository;
    private final ColorRepository colorRepository;
//    private final ProductImageRepository productImageRepository;
    private final ProductSizeRepository productSizeRepository;

    public ProductService(ProductRepository productRepository,
                          UserRepository customerRepository,
                          ColorRepository colorRepository,
//                          ProductImageRepository productImageRepository,
                          ProductSizeRepository productSizeRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.colorRepository = colorRepository;
//        this.productImageRepository = productImageRepository;
        this.productSizeRepository = productSizeRepository;
    }

    @Transactional
    public SuccessResponse create(ProductRequest productRequest) {
        User user = AuthUser.getPrincipal(); // authenticated user object
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

        if(productRequest.getColors() != null && !productRequest.getColors().isEmpty()) {
            for (ColorRequest color : productRequest.getColors()) {
                ProductColor existingColor = colorRepository
                        .findByColor(color.getColor())
                        .orElse(new ProductColor(color.getColor()));

                savedProduct.addProductColor(existingColor);
            }
        }

        if(productRequest.getSizes() != null & !productRequest.getSizes().isEmpty()) {
            for (ProductSizeRequest size : productRequest.getSizes()) {
                ProductSize existingSize = productSizeRepository
                        .findBySize(size.getSize())
                        .orElse(new ProductSize(size.getSize()));

                savedProduct.addProductSize(existingSize);
            }
        }

        if(productRequest.getImages() != null & !productRequest.getImages().isEmpty()) {
            for (ProductImageRequest image : productRequest.getImages()) {
                savedProduct.addProductImage(new ProductImage(image.getUrl()));
            }
        }

        return new SuccessResponse("Product created succesfully", HttpStatus.CREATED.value());
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .slug(product.getSlug())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .sizes(product.getSizes().stream().map(s -> new ProductSizeRequest(s.getSize())).toList())
                        .images(product.getImages().stream().map(i -> new ProductImageRequest(i.getUrl())).toList())
                        .colors(product.getColors().stream().map(c -> new ColorRequest(c.getColor())).toList())
                        .updatedAt(product.getUpdatedAt())
                        .createdAt(product.getCreatedAt())
                        .build()).toList();
    }
}
