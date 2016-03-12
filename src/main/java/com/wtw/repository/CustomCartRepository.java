package com.wtw.repository;

import com.wtw.domain.Cart;
import com.wtw.domain.CartItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface CustomCartRepository {
    @Transactional
    Cart createCart(Cart cart);

    @Transactional
    @Modifying
    Cart updateCart(List<CartItem> cartItems, BigDecimal grandTotal, Long id);
}
