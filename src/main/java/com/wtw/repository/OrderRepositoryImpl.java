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
        logger.info("Zamowienie przed zmiana wyglada = "+order.toString());
        order.setCart(cart);
        order.setUserId(userId);
        order.setShippingDetail(shippingDetail);
        logger.info("Zmienione zamowienie powinno miec taki koszyk = "+cart.toString()
                +" takie dane do wysylki = "+shippingDetail.toString()+" i takie id uzytkownika = "+userId);
        order=orderRepository.save(order);
        logger.info("Tak wyglada po zmianie = "+order.toString());
        return order;
    }
}
