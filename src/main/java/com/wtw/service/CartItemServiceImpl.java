package com.wtw.service;


import com.wtw.domain.CartItem;
import com.wtw.repository.CartItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private static final Logger logger = LoggerFactory.getLogger(CartItemService.class);
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Optional<CartItem> getCartItemById(long id) {
        logger.info("Metoda = getCartItemById");
        return Optional.ofNullable(cartItemRepository.findCartItemById(id));
    }

    @Override
    public Collection<CartItem> getAllCartItems() {
        logger.info("Metoda = getAllCartItems");
        return (Collection<CartItem>) cartItemRepository.findAll();
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        logger.info("Metoda = createCartItem");
        CartItem saveCartItem = cartItemRepository.save(cartItem);
        logger.info("Zapisany cartItem to " + saveCartItem.toString());
        return saveCartItem;
    }
}
