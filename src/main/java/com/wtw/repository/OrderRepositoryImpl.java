package com.wtw.repository;


import com.wtw.domain.Cart;
import com.wtw.domain.Order;
import com.wtw.domain.ShippingDetail;
import com.wtw.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

public class OrderRepositoryImpl implements CustomOrderRepository {
    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    @Modifying
    public Order createOrder(Order order) {
        logger.info("Tworzymy nowe zamowienie = "+order.toString());
        return orderRepository.save(order);
    }

    @Transactional
    @Modifying

    public Order updateOrder(Cart cart, Long userId, ShippingDetail shippingDetail, Long id) {
        logger.info("Aktualizacja zamowienia");
        Order order=orderRepository.findOrderById(id);
        order.setCart(cart);
        order.setUserId(userId);
        order.setShippingDetail(shippingDetail);
        order=orderRepository.save(order);
        return order;
    }
}
