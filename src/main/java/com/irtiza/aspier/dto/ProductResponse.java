package com.irtiza.aspier.dto;


import com.irtiza.aspier.request.ColorRequest;
import com.irtiza.aspier.request.ProductImageRequest;
import com.irtiza.aspier.request.ProductSizeRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String slug;
    private String name;
    private String description;
    private float price;
    private List<ColorRequest> colors;
    private List<ProductImageRequest> images;
    private List<ProductSizeRequest> sizes;

    private LocalDate createdAt;
    private LocalDate updatedAt;
}
