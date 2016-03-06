package com.wtw.api;

import com.wtw.domain.Product;
import com.wtw.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ProductListFactory {
    private final ProductRepository repository;

    public ProductListFactory(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> buildNumberOfProductsAndSave(int numberOfProducts) {
        List<Product> productList = new ArrayList<>();
        IntStream.range(0, numberOfProducts).forEachOrdered(number -> {
            Product product = new ProductBuilder(String.format("product_%s", number)).build();
            repository.save(product);
            productList.add(new Product(product));
        });
        return productList;
    }

}
