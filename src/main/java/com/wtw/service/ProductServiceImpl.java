package com.wtw.service;

import com.wtw.domain.Category;
import com.wtw.domain.Product;
import com.wtw.form.ProductCreateForm;
import com.wtw.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private static int PAGE_LIMIT = 5;
    private static int MAX_PRODUCT_ON_PAGE = 4;
    private static String DEFAULT_SORT_BY_ID = "id";
    private static int FIRST_PAGE = 0;
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> getProductById(long id) {
        return Optional.ofNullable(productRepository.findOne(id));
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        return productRepository.findProductByName(name);
    }

    @Override
    public Collection<Product> getAllProducts() {
        return (Collection<Product>) productRepository.findAll();
    }

    @Override
    public Product create(ProductCreateForm form) {
        Product product = new Product(form.getName(), form.getDescription(), new BigDecimal(form.getPrice()), Long.parseLong(form.getUnitsInStock()), form.getImageURL());
        return productRepository.save(product);
    }

    @Override
    public Page<Product> findAllPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findPageableByCategory(Pageable pageable, Category category) {
        return productRepository.findPageableByCategory(pageable, category);
    }

    public List<Product> getProducts(final Integer page, final Integer size, final String sort) {
        PageRequest pageRequest = new PageRequest(setReturnPage(page), setProductOnPageLimit(setReturnSize(size)), setSortDirection(sort), DEFAULT_SORT_BY_ID);
        return productRepository.findAll(pageRequest).getContent().stream().map(Product::new).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteProductById(id);
    }

    @Override
    public void createProduct(Product product) {
        productRepository.createProduct(product);
    }

    @Override
    public void updateProduct(Long id, Product product) {
        productRepository.updateProduct(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getUnitsInStock(),
                product.getImageURL(),
                id);
    }

    private int setReturnPage(final Integer pageNumber) {
        return (Objects.isNull(pageNumber) ? FIRST_PAGE : pageNumber.intValue() - 1);
    }

    private int setReturnSize(final Integer size) {
        return (Objects.isNull(size) ? PAGE_LIMIT : size.intValue());
    }

    private int setProductOnPageLimit(final Integer size) {
        return (size > MAX_PRODUCT_ON_PAGE ? MAX_PRODUCT_ON_PAGE : size.intValue());
    }

    private Sort.Direction setSortDirection(final String sort) {
        return (StringUtils.isEmpty(sort) ? null : Sort.Direction.fromString(sort));
    }
}
