package com.wtw.controller;

import com.wtw.domain.User;
import com.wtw.form.UserCreateForm;
import com.wtw.service.UserService;
import com.wtw.validator.UserCreateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private UserCreateValidator userCreateValidator;

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String registerPage(Model model) {
        logger.info("Wywolanie widoku rejestracji");
        model.addAttribute("userCreateForm", new UserCreateForm());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerSubmit(@ModelAttribute @Valid UserCreateForm userCreateForm, BindingResult result, WebRequest request) {
        logger.info("Dodanie uzytkownika " + userCreateForm.toString());
        userCreateValidator.validate(userCreateForm, result);
        if (result.hasErrors()) {
            logger.info("Blad przy dodawaniu uzytkownika\n" + result.getAllErrors().toString() + "\n");
            return "register";
        } else {
            logger.info("Pomyslne dodanie uzytkownika");
            User user = userService.create(userCreateForm);
            return "index";
        }

    }
}
