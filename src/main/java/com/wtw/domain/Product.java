package com.wtw.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Admin on 2016-02-26.
 */
@Entity
public class Product implements Serializable {
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final long unitsInStock;
    private final long unitsInOrder;
    private final String imageURL;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idCartItem")
    private CartItem cartItem;

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
            @JsonProperty("imageURL") String imageURL
    ) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = null;
        this.unitsInStock = unitsInStock;
        this.unitsInOrder = 0;
        this.imageURL = imageURL;
    }

    @JsonCreator
    public Product(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("unitsInStock") long unitsInStock,
            @JsonProperty("imageURL") String imageURL,
            @JsonProperty("category") Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.unitsInStock = unitsInStock;
        this.unitsInOrder = 0;
        this.imageURL = imageURL;
    }


    public Product(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.unitsInStock = product.getUnitsInStock();
        this.unitsInOrder = 0;
        this.imageURL = product.getImageURL();
    }

    public Long getId() {
        return id;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", unitsInStock=" + unitsInStock +
                ", unitsInOrder=" + unitsInOrder +
                ", imageURL='" + imageURL + '\'' +
                ", id=" + id +
                '}';
    }
}

