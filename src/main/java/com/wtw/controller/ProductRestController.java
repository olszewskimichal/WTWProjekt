package com.wtw.controller;

import com.wtw.domain.Product;
import com.wtw.domain.User;
import com.wtw.service.ProductService;
import com.wtw.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    private static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);
    private final ProductService productService;

    private final UserService userService;

    @Autowired
    public ProductRestController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @RequestMapping(value = "/allProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProducts() {
        logger.info(productService.getAllProducts().toString());
        logger.info("pobieramy wszystkie produkty");
        return (List<Product>) productService.getAllProducts();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{productName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProduct(@PathVariable("productName") String productName) {
        logger.info(productService.getAllProducts().toString());
        logger.info("pobieramy produkt o nazwie" + productName + " wynikiem jest = " + productService.getProductByName(productName));
        return productService.getProductByName(productName).
                orElseThrow(() -> new NoSuchElementException(String.format("Produkt o danej nazwie = $d nie istnieje", productName)));
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProducts(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit, @RequestParam(value = "order", required = false) String sort) {
        logger.info(productService.getAllProducts().toString());
        logger.info("pobieramy produkty wg niektorych szczegolow " + productService.getProducts(page, limit, sort).toString());
        return productService.getProducts(page, limit, sort);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        logger.info("update produktu o ide " + id + " " + product.toString());
        productService.updateProduct(id, product);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addProduct(@RequestBody Product product) {
        productService.createProduct(product);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }


    @RequestMapping(value = "/allUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return (List<User>) userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable("id") long userId) {
        return userService.getUserById(userId).
                orElseThrow(() -> new NoSuchElementException(String.format("Uzytkownik o id =%s nie istnieje", userId)));
    }


}
