package com.irtiza.aspier.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ProductRequest {
    @NotBlank(message = "slug is required")
    private String slug;
    @NotBlank(message = "name is required")
    private String name;
    @NotNull(message = "price is required")
    private float price;

    private String description;
    private List<ColorRequest> colors;

    public ProductRequest(String slug, String name, float price, String description, List<ColorRequest> colors) {
        this.slug = slug;
        this.name = name;
        this.price = price;
        this.description = description;
        this.colors = colors;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ColorRequest> getColors() {
        return colors;
    }

    public void setColors(List<ColorRequest> colors) {
        this.colors = colors;
    }
}
