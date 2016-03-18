package com.wtw.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category implements Serializable {
    private final String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "categoryId")
    private Long id;

    @OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Product> products;

    public Category() {
        this.products = null;
        this.name = null;
    }

    public Category(String name) {
        this.products = new HashSet<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public Set<Product> getProducts() {
        return products;
    }
    

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
