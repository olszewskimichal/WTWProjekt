package com.wtw.controller.rest;

import com.wtw.domain.Product;
import com.wtw.service.CategoryService;
import com.wtw.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    private static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);
    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 6;
    private static final String ALL_CATEGORY = "all";
    private final ProductService productService;
    private final CategoryService categoryService;


    @Autowired
    public ProductRestController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/allProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProducts() {
        logger.info("Pobierz wszystkie produkty " + productService.getAllProducts().toString());
        return (List<Product>) productService.getAllProducts();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProducts(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit,
                                     @RequestParam(value = "order", required = false) String sort, @RequestParam(value = "category", required = false) String category) {
        int evalPageSize = limit == null ? INITIAL_PAGE_SIZE : limit;
        int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;
        logger.info("evalPage = " + evalPage + " evalPageSize = " + evalPageSize + " kategoria= " + category);
        if (category == null || category.equals("") || category.equals("all") || category.equals("null")) {
            logger.info("NULL Pobierz produkty wg niektorych szczegolow " + productService.findAllPageable(new PageRequest(evalPage, evalPageSize)).getContent().toString());
            return productService.findAllPageable(new PageRequest(evalPage, evalPageSize)).getContent();
        } else {
            System.out.println("chuj" + category);
            logger.info("Pobierz produkty wg niektorych szczegolow " + productService.findPageableByCategory(new PageRequest(evalPage, evalPageSize), categoryService.getCategoryByName(category)).getContent().toString());
            return productService.findPageableByCategory(new PageRequest(evalPage, evalPageSize), categoryService.getCategoryByName(category)).getContent();
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Product> getProductsByCategory(@RequestParam(value = "category", required = false) String categoryName) {
        logger.info("Pobierz produkty nalezace do kategorii o nazwie " + categoryName);
        if (categoryName == null)
            return productService.getAllProducts();
        else {
            return categoryService.getCategoryByName(categoryName).getProducts();
        }
    }


    @CrossOrigin
    @RequestMapping(value = "/get/totalPages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer getTotalPages(@RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "limit", required = false) Integer limit,
                                 @RequestParam(value = "category", required = false) String category) {
        int evalPageSize = limit == null ? INITIAL_PAGE_SIZE : limit;
        int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;
        if (category == null || category.equals("") || category.equals("all") || category.equals("null")) {
            return productService.findAllPageable(new PageRequest(evalPage, evalPageSize)).getTotalPages();
        } else {
            return productService.findPageableByCategory(new PageRequest(evalPage, evalPageSize), categoryService.getCategoryByName(category)).getTotalPages();
        }
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
