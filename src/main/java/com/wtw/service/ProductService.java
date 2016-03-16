package com.wtw.service;

import com.wtw.domain.Category;
import com.wtw.domain.Product;
import com.wtw.form.ProductCreateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 2016-03-01.
 */
public interface ProductService {
    Optional<Product> getProductById(long id);

    Optional<Product> getProductByName(String name);

    Collection<Product> getAllProducts();

    Product create(ProductCreateForm form);

    Page<Product> findAllPageable(Pageable pageable);

    Page<Product> findPageableByCategory(Pageable pageable, Category category);

    public List<Product> getProducts(final Integer page, final Integer size, final String sort);

    public void deleteProduct(final Long id);

    public void createProduct(final Product product);

    public void updateProduct(final Long id, final Product product);
}
