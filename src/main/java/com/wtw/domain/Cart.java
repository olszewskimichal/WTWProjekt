package com.wtw.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Cart implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "Id_CART")
    private Long id;
    @OneToMany(targetEntity = CartItem.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<CartItem> cartItems;
    private BigDecimal grandTotal;

    public Cart() {
        cartItems = new ArrayList<>();
        grandTotal = new BigDecimal(0);
    }

    public void addCartItem(CartItem item) {
        Long productId = item.getProduct().getId();
        List<CartItem> result = new ArrayList<>();
        boolean dodaj = true;
        if (!cartItems.isEmpty()) {
            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct().getId() == productId) {
                    cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
                    result.add(cartItem);
                    dodaj = false;
                } else {
                    result.add(cartItem);
                }
            }
        } else {
            dodaj = false;
            result.add(item);
        }
        if (dodaj) {
            result.add(item);
        }
        this.cartItems = result;
        updateGrandTotal();
    }

    public void removeCartItem(Product item) {
        Long productId = item.getId();
        this.cartItems = cartItems.stream().filter(e -> e.getProduct().getId() != productId).collect(Collectors.toList());
        updateGrandTotal();
    }

    public void updateGrandTotal() {
        grandTotal = cartItems.stream().map(e -> e.getTotalPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", cartItems=" + cartItems +
                ", grandTotal=" + grandTotal +
                '}';
    }
}
