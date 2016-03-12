package com.wtw.repository;

import com.wtw.domain.CartItem;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Admin on 2016-03-09.
 */
public interface CustomCartItemRepository {
    @Transactional
    CartItem createCartItem(CartItem cartItem);
}
