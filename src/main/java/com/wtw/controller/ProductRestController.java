package com.wtw.controller;

import com.wtw.domain.Product;
import com.wtw.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    private static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);
    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/allProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProducts() {
        logger.info("Pobierz wszystkie produkty " + productService.getAllProducts().toString());
        return (List<Product>) productService.getAllProducts();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProducts(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit, @RequestParam(value = "order", required = false) String sort) {
        logger.info("Pobierz produkty wg niektorych szczegolow " + productService.getProducts(page, limit, sort).toString());
        return productService.getProducts(page, limit, sort);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/product/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        logger.info("Zaktualizuj produkt o id " + id + " " + product.toString());
        productService.updateProduct(id, product);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addProduct(@RequestBody Product product) {
        logger.info("Dodaj produkt " + product.toString());
        productService.createProduct(product);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        logger.info("Usun produkt o id " + id);
        productService.deleteProduct(id);
    }

}
