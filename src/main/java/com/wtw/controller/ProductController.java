package com.wtw.controller;

import com.wtw.domain.Pager;
import com.wtw.domain.Product;
import com.wtw.form.ProductCreateForm;
import com.wtw.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.NoSuchElementException;

/**
 * Created by Admin on 2016-02-28.
 */
@Controller
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 4;
    private static final int[] PAGE_SIZES = {4, 8, 12};


    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public String addProductPage(Model model) {
        logger.info("Wczytanie widoku z dodaniem produktu");
        model.addAttribute("productCreateForm", new ProductCreateForm());
        return "addProduct";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String showProductsPage(Model model, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        logger.info(page + " " + pageSize);
        int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
        int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;
        Page<Product> products = productService.findAllPageable(new PageRequest(evalPage, evalPageSize));
        Pager pager = new Pager(products.getTotalPages(), products.getNumber(), BUTTONS_TO_SHOW);
        model.addAttribute("products", products);
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);
        logger.info(products.getNumberOfElements() + "");
        return "products";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String createProductSubmit(@ModelAttribute @Valid ProductCreateForm productCreateForm, BindingResult result, WebRequest request) {
        logger.info("Dodanie produktu " + productCreateForm.toString());
        if (result.hasErrors()) {
            logger.info("Błedne dodawanie produktu " + result.getAllErrors().toString());
            return "addProduct";
        } else {
            Product product = productService.create(productCreateForm);
            return "index";
        }
    }

    @RequestMapping(value = "/products/product/{id}", method = RequestMethod.GET)
    public String showProductDetail(@PathVariable Long id, Model model) {
        logger.info("Wyswietlenie szczegółów produktu o id= " + id);
        try {
            Product product = productService.getProductById(id)
                    .orElseThrow(() -> new NoSuchElementException(String.format("Produkt o id =%s nie istnieje", id)));
            model.addAttribute(product);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.info(id.toString() + "\n" + model);
        }
        return "product";
    }

    @RequestMapping(value = "/testProduct")
    public String test() {
        return "test";
    }

    @RequestMapping(value = "/testUser")
    public String test2() {
        return "test2";
    }

}
