package com.wtw.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "OrderTable")
public class Order implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
    private Cart cart;

    private Long userId;

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
    private ShippingDetail shippingDetail;

    public Order() {
        this.cart=null;
        this.userId=null;
        this.shippingDetail=new ShippingDetail();
    }

    public Order(Cart cart, Long userId) {
        this.cart = cart;
        this.userId = userId;
        this.shippingDetail = new ShippingDetail();
    }

    public Order(Cart cart, Long userId, ShippingDetail shippingDetail) {
        this.cart = cart;
        this.userId = userId;
        this.shippingDetail = shippingDetail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ShippingDetail getShippingDetail() {
        return shippingDetail;
    }

    public void setShippingDetail(ShippingDetail shippingDetail) {
        this.shippingDetail = shippingDetail;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", cartId=" + cart.toString() +
                ", userId=" + userId +
                '}';
    }
}
