package com.irtiza.aspier.mappers;

import com.irtiza.aspier.entity.ProductColor;
import com.irtiza.aspier.entity.ProductImage;
import com.irtiza.aspier.entity.ProductSize;
import com.irtiza.aspier.request.ColorRequest;
import com.irtiza.aspier.request.ProductImageRequest;
import com.irtiza.aspier.request.ProductSizeRequest;

public class ProductMapper {


    public static ColorRequest mapToRequest(ProductColor color) {
        return new ColorRequest(color.getColor());
    }

    public static ProductImageRequest mapToRequest(ProductImage image) {
        return new ProductImageRequest(image.getUrl());
    }

    public static ProductSizeRequest mapToRequest(ProductSize size) {
        return new ProductSizeRequest(size.getSize());
    }
}
