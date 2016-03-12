package com.wtw.exception;

public class InvalidCartException extends RuntimeException {
    private Long cartId;

    public InvalidCartException(Long cartId) {
        this.cartId = cartId;
    }

    public Long getCartId() {
        return cartId;
    }
}
