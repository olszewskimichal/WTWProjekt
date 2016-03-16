package com.wtw.repository;

import com.wtw.domain.Category;
import com.wtw.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Created by Admin on 2016-03-01.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, CustomProductRepository {
    Optional<Product> findProductByName(String name);

    Product findProductById(Long id);

    @Transactional
    Long deleteProductById(Long id);

    Page<Product> findPageableByCategory(Pageable pageable, Category category);

    @Transactional
    @Modifying
    @Query("update Product p set p.name = ?1, p.description = ?2, p.price = ?3, p.unitsInStock=?4, p.imageURL=?5 where p.id = ?6")
    int updateProduct(String name, String description, BigDecimal price, Long unitsInStock, String imageURL, Long id);
}
