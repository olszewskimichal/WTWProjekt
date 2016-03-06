package com.wtw.repository;

import com.wtw.domain.Product;
import org.springframework.transaction.annotation.Transactional;

public interface CustomProductRepository {
    @Transactional
    <S extends Product> S createProduct(S product);
}
