package com.wtw.controller;

import com.wtw.form.ContactForm;
import com.wtw.validator.RecaptchaFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private RecaptchaFormValidator recaptchaFormValidator;

    @RequestMapping("/contact")
    public String contactPage(Model model) {
        logger.info("Strona kontaktowa");
        model.addAttribute("contactForm", new ContactForm());
        return "contact";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String registerSubmit(@ModelAttribute @Valid ContactForm contactForm, BindingResult result, Model model) {
        logger.info("Przesłanie formularza kontaktowego " + contactForm.toString());
        recaptchaFormValidator.validate(contactForm, result);
        if (result.hasErrors()) {
            logger.info("Blad przy przesyłaniu formularza \n" + result.getAllErrors().toString() + "\n");
            return "contact";
        } else {
            model.addAttribute("contactSuccess", true);
            model.addAttribute("contactForm", new ContactForm());
            return "contact";
        }

    }
}
