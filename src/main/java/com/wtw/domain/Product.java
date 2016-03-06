package com.wtw.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by Admin on 2016-02-26.
 */
@Entity
public class Product {
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final String category;
    private final long unitsInStock;
    private final long unitsInOrder;
    private final String imageURL;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Product() {
        this.name = null;
        this.description = null;
        this.price = null;
        this.category = null;
        this.unitsInStock = 0;
        this.unitsInOrder = 0;
        this.imageURL = null;
    }

    @JsonCreator
    public Product(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("unitsInStock") long unitsInStock,
            @JsonProperty("imageURL") String imageURL) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = null;
        this.unitsInStock = unitsInStock;
        this.unitsInOrder = 0;
        this.imageURL = imageURL;
    }


    public Product(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.category = null;
        this.unitsInStock = product.getUnitsInStock();
        this.unitsInOrder = 0;
        this.imageURL = product.getImageURL();
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public long getUnitsInStock() {
        return unitsInStock;
    }

    public String getImageURL() {
        return imageURL;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", unitsInStock=" + unitsInStock +
                ", unitsInOrder=" + unitsInOrder +
                ", imageURL='" + imageURL + '\'' +
                ", id=" + id +
                '}';
    }
}

