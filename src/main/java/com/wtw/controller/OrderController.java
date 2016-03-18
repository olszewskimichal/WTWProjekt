package com.wtw.controller;


import com.wtw.domain.Cart;
import com.wtw.domain.CurrentUser;
import com.wtw.domain.Order;
import com.wtw.domain.ShippingDetail;
import com.wtw.form.CustomerInfoForm;
import com.wtw.service.CartService;
import com.wtw.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @RequestMapping("/test")
    public String collectCustomerrInfoPage(final Model model) {
        logger.info("Wywołanie widoku - collectCustomerInfoPage");
        model.addAttribute("customerInfoForm", new CustomerInfoForm());
        return "collectCustomerInfo";
    }

    @RequestMapping
    public String get() {
        final Cart cart = cartService.getShoppingCartInSession();
        final CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final Long userId = user.getId();
        final Order order = orderService.getOrderInSession(cart, userId);
        logger.info(order.toString());
        return "redirect:/order/collectCustomerInfo";
    }

    @RequestMapping("/collectCustomerInfo")
    public String collectCustomerInfoPage(final Model model) {
        logger.info("Wywołanie widoku - collectCustomerInfoPage");
        model.addAttribute("customerInfoForm", new CustomerInfoForm());
        return "collectCustomerInfo";
    }

    @RequestMapping(value = "/collectCustomerInfo", method = RequestMethod.POST)
    public String registerSubmit(@ModelAttribute @Valid final CustomerInfoForm customerInfoForm, final BindingResult result, final WebRequest request) {
        logger.info("Zapisanie danych do wysyłki " + customerInfoForm.toString());
        if (result.hasErrors()) {
            logger.info("Blad przy dodawaniu danych do wysyłki \n" + result.getAllErrors().toString() + "\n");
            return "collectCustomerInfo";
        } else {
            Order order=orderService.getOrderInSession();
            order.setShippingDetail(new ShippingDetail(customerInfoForm));
            orderService.updateOrder(order.getId(),order);
            logger.info("Pomyslne dodanie danych do wysyłki");
            return "redirect:/order/orderConfirmation";
        }
    }

    @RequestMapping(value = "/orderConfirmation")
    public String orderConfirmationPage(final Model model){
        logger.info("Wywolanie widoku - orderConfirmationPage");
        model.addAttribute(orderService.getOrderInSession());
        return "orderConfirmation";
    }

}
