package com.wtw.repository;


import com.wtw.domain.Cart;
import com.wtw.domain.Order;
import com.wtw.domain.ShippingDetail;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

public interface CustomOrderRepository {
        @Transactional
        Order createOrder(Order cart);

        @Transactional
        @Modifying
        Order updateOrder(Cart cart, Long userId, ShippingDetail shippingDetail, Long id);

}
