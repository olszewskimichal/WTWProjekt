package com.wtw.repository;


import com.wtw.domain.CartItem;
import org.springframework.beans.factory.annotation.Autowired;

public class CartItemRepositoryImpl implements CustomCartItemRepository {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }
}
