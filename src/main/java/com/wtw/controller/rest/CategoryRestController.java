package com.wtw.controller.rest;

import com.wtw.domain.Category;
import com.wtw.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryRestController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryRestController.class);
    private final CategoryService categoryService;


    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> getAllCategory() {
        logger.info("Pobierz wszystkie kategorie " + categoryService.getAllCategory().toString());
        return categoryService.getAllCategory();
    }

}
