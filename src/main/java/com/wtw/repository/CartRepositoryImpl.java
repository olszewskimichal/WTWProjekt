package com.wtw.repository;


import com.wtw.domain.Cart;
import com.wtw.domain.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public class CartRepositoryImpl implements CustomCartRepository {
    @Autowired
    private CartRepository cartRepository;

    @Transactional
    @Modifying
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Transactional
    @Modifying
    public Cart updateCart(List<CartItem> cartItems, BigDecimal grandTotal, Long id) {
        Cart cart = cartRepository.findCartById(id);
        cart.setCartItems(cartItems);
        cart.setGrandTotal(grandTotal);
        cart = cartRepository.save(cart);
        return cart;
    }


}
