package com.wtw.controller.rest;

import com.wtw.domain.Cart;
import com.wtw.domain.CartItem;
import com.wtw.domain.Product;
import com.wtw.service.CartItemService;
import com.wtw.service.CartService;
import com.wtw.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/carts/cart")
public class CartRestController {
    private static final Logger logger = LoggerFactory.getLogger(CartRestController.class);
    private final CartService cartService;
    private final ProductService productService;
    private final CartItemService cartItemService;

    @Autowired
    public CartRestController(CartService cartService, ProductService productService, CartItemService cartItemService) {
        this.productService = productService;
        this.cartService = cartService;
        this.cartItemService = cartItemService;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCart(@RequestBody Cart cart) {
        logger.info("Dodaj koszyk " + cart.toString());
        cartService.createCart(cart);
    }

    @CrossOrigin
    @RequestMapping(value = "/{cartId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Cart getCartById(@PathVariable(value = "cartId") Long cartId) {
        logger.info("Pobierz koszyk o id= " + cartService.getCartById(cartId));
        return cartService.getCartById(cartId)
                .orElseThrow(() -> new NoSuchElementException(String.format("Koszyk o id =%s nie istnieje", cartId)));
    }

    @CrossOrigin
    @RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateCart(@PathVariable(value = "cartId") Long cartId, @RequestBody Cart cart) {
        logger.info("Zaktualizuj koszyk o id= " + cartId + "\n" + cart.toString());
        cartService.updateCart(cartId, cart);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/{cartId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("cartId") Long id) {
        logger.info("Usun elementy z koszyka o id " + id);
        cartService.deleteElementsFromCart(id);
    }

    @RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addItem(@PathVariable Long productId) {
        logger.info("Dodaj produkt o id " + productId);
        Cart cart = cartService.getShoppingCartInSession();
        Product product = productService.getProductById(productId).orElse(new Product());
        logger.info("Dodaj produkt =" + product.toString() + "\n do koszyka = " + cart.toString());
        CartItem cartItem = new CartItem(product);
        logger.info("Cart item = " + cartItem);
        cartService.addCartItem(cartItem);
        cartService.updateCart(cart.getId(), cart);
    }

    @CrossOrigin
    @RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable Long productId) {
        logger.info("usuwamy produkt o id " + productId);
        Cart cart = cartService.getShoppingCartInSession();
        Product product = productService.getProductById(productId)
                .orElseThrow(() -> new NoSuchElementException(String.format("Produkt o id =%s nie istnieje", productId)));
        logger.info("Usun produkt =" + product.toString() + "\n z koszyka = " + cart.toString());
        cartService.removeCartItem(product);
        cartService.updateCart(cart.getId(), cart);
    }

    @CrossOrigin
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public int getId() {
        Cart cart = cartService.getShoppingCartInSession();
        return cart.getId().intValue();
    }


    @CrossOrigin
    @RequestMapping(value = "/updateQuantity", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void changeQuantity(@RequestParam(value = "idProd", required = true) Long id, @RequestParam(value = "quantity", required = true) Integer quantity) {
        logger.info("Zmien ilosc itemu o id " + id + " na ilosc = " + quantity);
        Cart cart = cartService.getShoppingCartInSession();
        List<CartItem> list = cart.getCartItems();
        List<CartItem> result = new ArrayList<>();
        for (CartItem item : list) {
            if (item.getId() == id) {
                item.setQuantity(quantity);
            }
            result.add(item);
        }
        cart.setCartItems(result);
        cart.updateGrandTotal();
        cartService.updateCart(cart.getId(), cart);
    }


}


