package com.wtw.service;


import com.wtw.domain.Cart;
import com.wtw.domain.Order;
import com.wtw.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);
    private static final String ORDER_ATTRIBUTE_NAME = "shoppingorder";
    private final OrderRepository orderRepository;
    private final HttpSession httpSession;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, HttpSession httpSession) {
        this.orderRepository = orderRepository;
        this.httpSession = httpSession;
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public Collection<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderInSession() {
        Order order = (Order) this.httpSession.getAttribute(ORDER_ATTRIBUTE_NAME);
        if(order==null){
            order=orderRepository.createOrder(new Order());
            this.httpSession.setAttribute(ORDER_ATTRIBUTE_NAME,order);
        }
        return order;
    }
    public Order getOrderInSession(Cart cart,Long userId){
        Order order = (Order) this.httpSession.getAttribute(ORDER_ATTRIBUTE_NAME);
        if(order==null){
            order=orderRepository.createOrder(new Order(cart,userId));
            this.httpSession.setAttribute(ORDER_ATTRIBUTE_NAME,order);
        }
        return order;
    }
    public void updateOrderInSession(Order order) {
        logger.info("updateOrderInSesssion-zapisuje w sesji nowy koszyk");
        logger.info(order.toString());
        this.httpSession.setAttribute(ORDER_ATTRIBUTE_NAME, order);
    }

    @Override
    public void createOrder(Order order) {
        updateOrderInSession(order);
        orderRepository.createOrder(order);
    }

    @Override
    public void updateOrder(Long id, Order order) {
        logger.info(order.toString());
        order=orderRepository.updateOrder(order.getCart(),order.getUserId(),order.getShippingDetail(),id);
        logger.info(order.toString());
        updateOrderInSession(order);
    }
}
