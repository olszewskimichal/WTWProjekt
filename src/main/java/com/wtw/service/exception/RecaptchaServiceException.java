package com.wtw.service.exception;

public class RecaptchaServiceException extends RuntimeException {
    public RecaptchaServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
