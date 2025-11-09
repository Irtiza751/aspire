package com.irtiza.aspier.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_size")
public class ProductSize {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String size;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductSize() {}

    public ProductSize(String size, Product product) {
        this.size = size;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public String getSize() {
        return size;
    }

    public Product getProduct() {
        return product;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
