package com.wtw.api;

import com.wtw.domain.Product;

import java.math.BigDecimal;

public class ProductBuilder {
    private int numberOfInstances = 1;

    private String name;
    private String description = "description";
    private BigDecimal price = BigDecimal.TEN;
    private long unitsInStock = 100;
    private String imageUrl = "http://localhost/image";


    public ProductBuilder(String name) {
        this.name = name;
    }

    public ProductBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Product build() {
        return new Product(name, description, price, unitsInStock, imageUrl);
    }

}