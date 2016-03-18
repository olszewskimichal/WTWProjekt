package com.wtw.service;


import com.wtw.domain.Cart;
import com.wtw.domain.Order;

import java.util.Collection;

public interface OrderService {
    Order getOrderById(Long id);

    Collection<Order> getAllOrders();

    Order getOrderInSession();

    Order getOrderInSession(Cart cart,Long userId);

    void createOrder(final Order order);

    void updateOrder(final Long id, final Order order);

}
