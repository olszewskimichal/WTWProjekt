package com.wtw.repository;

import com.wtw.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;


final public class ProductRepositoryImpl implements CustomProductRepository {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public <S extends Product> S createProduct(S product) {
        if (Objects.nonNull(productRepository) && productRepository.findProductById(product.getId()) == null) {
            return productRepository.save(product);
        }
        return null;
    }
}
