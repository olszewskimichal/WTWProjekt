package com.wtw.service;

public interface RecaptchaService {
    boolean isResponseValid(String remoteIp, String response);
}
