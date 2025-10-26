package com.irtiza.aspier.request;

public class ProductRequest {
    private String slug;
    private String name;
    private float price;
    private String description;

    public ProductRequest() {}

    public ProductRequest(String slug, String name, float price, String description) {
        this.slug = slug;
        this.name = name;
        this.price = price;
        this.description = description;
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
}
