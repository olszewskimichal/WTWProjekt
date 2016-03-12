package com.wtw.service;


import com.wtw.domain.Cart;
import com.wtw.domain.CartItem;
import com.wtw.domain.Product;

import java.util.Collection;
import java.util.Optional;

public interface CartService {
    Optional<Cart> getCartById(long id);

    Collection<Cart> getAllCarts();

    Cart validate(Long cartId);

    Cart getShoppingCartInSession();

    Cart addCartItem(CartItem item);

    Cart removeCartItem(Product item);

    void deleteElementsFromCart(final Long id);

    void createCart(final Cart cart);

    void updateCart(final Long id, final Cart cart);
}
