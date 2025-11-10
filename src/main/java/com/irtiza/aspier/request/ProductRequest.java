package com.irtiza.aspier.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ProductRequest {
    @NotBlank(message = "slug is required")
    private String slug;
    @NotBlank(message = "name is required")
    private String name;
    @NotNull(message = "price is required")
    private float price;
    // nullable fields
    private String description;
    private List<ColorRequest> colors;
    private List<ProductSizeRequest> sizes;
    private List<ProductImageRequest> images;
}
