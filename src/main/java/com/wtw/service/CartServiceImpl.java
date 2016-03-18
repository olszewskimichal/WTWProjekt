package com.wtw.service;

import com.wtw.domain.Cart;
import com.wtw.domain.CartItem;
import com.wtw.domain.Product;
import com.wtw.exception.InvalidCartException;
import com.wtw.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);
    private static final String CART_ATTRIBUTE_NAME = "shoppingcart";
    private final CartRepository cartRepository;
    @Autowired
    private HttpSession httpSession;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart getShoppingCartInSession() {
        Cart cart = (Cart) this.httpSession.getAttribute(CART_ATTRIBUTE_NAME);
        if (cart == null) {
            cart = cartRepository.createCart(new Cart());
            this.httpSession.setAttribute(CART_ATTRIBUTE_NAME, cart);
        }
        return cart;
    }

    @Override
    public Cart addCartItem(CartItem item) {
        logger.info("Metoda addCartItem");
        Cart cart = getShoppingCartInSession();
        cart.addCartItem(item);
        logger.info("Koszyk po dodaniu produktu wyglada =" + cart.toString());
        updateCartInSession(cart);
        return cart;
    }

    @Override
    public Cart removeCartItem(Product item) {
        logger.info("Metoda removeCartItem " + item.getId());
        Cart cart = getShoppingCartInSession();
        cart.removeCartItem(item);
        updateCartInSession(cart);
        return cart;
    }

    public void updateCartInSession(Cart cart) {
        logger.info("updateCartInSesssion-zapisuje w sesji nowy koszyk");
        logger.info(cart.toString());
        this.httpSession.setAttribute(CART_ATTRIBUTE_NAME, cart);
    }

    @Override
    public Optional<Cart> getCartById(long id) {
        return Optional.ofNullable(cartRepository.findCartById(id));
    }

    @Override
    public Collection<Cart> getAllCarts() {
        return (Collection<Cart>) cartRepository.findAll();
    }

    @Override
    public Cart validate(Long cartId) {
        logger.info("Walidacja koszyka ?"+cartId);
        Cart cart = cartRepository.findCartById(cartId);
        logger.info("Walidacja koszyk wyglada = "+cart.toString());
        if (cart == null || cart.getCartItems().size() == 0) {
            throw new InvalidCartException(cartId);
        }
        return cart;
    }

    @Override
    public void deleteElementsFromCart(Long id) {
        Cart cart = cartRepository.findCartById(id);
        cart.setCartItems(new ArrayList<>());
        cart.setGrandTotal(BigDecimal.ZERO);
        logger.info("delete elements"+cart.toString());
        cartRepository.updateCart(cart.getCartItems(), cart.getGrandTotal(), id);
        updateCartInSession(cart);
    }

    @Override
    public void createCart(Cart cart) {
        updateCartInSession(cart);
        cartRepository.createCart(cart);
    }

    @Override
    public void updateCart(Long id, Cart cart) {
        logger.info(cart.toString());
        logger.info(id + "");
        cart = cartRepository.updateCart(
                cart.getCartItems(),
                cart.getGrandTotal(),
                id
        );
        logger.info(cart.toString());
        updateCartInSession(cart);
    }
}
