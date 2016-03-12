package com.wtw.service;

import com.wtw.domain.CartItem;

import java.util.Collection;
import java.util.Optional;

public interface CartItemService {
    Optional<CartItem> getCartItemById(long id);

    Collection<CartItem> getAllCartItems();

    CartItem createCartItem(final CartItem cartItem);
}
