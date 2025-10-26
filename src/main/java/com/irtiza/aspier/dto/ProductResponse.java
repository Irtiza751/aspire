package com.irtiza.aspier.dto;


import java.time.LocalDate;

public class ProductResponse {
    private Long id;
    private String slug;
    private String name;
    private String description;
    private float price;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    public ProductResponse() {}

    public ProductResponse(Long id, String slug, String name, String description, float price, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.slug = slug;
        this.name = name;
        this.description = description;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
