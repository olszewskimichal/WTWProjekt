package com.wtw.form;

import org.hibernate.validator.constraints.NotEmpty;

public abstract class RecaptchaForm {
    @NotEmpty
    private String recaptchaResponse;

    public String getRecaptchaResponse() {
        return recaptchaResponse;
    }

    public void setRecaptchaResponse(String response) {
        this.recaptchaResponse = response;
    }

    @Override
    public String toString() {
        return "RecaptchaForm{" +
                "recaptchaResponse='" + recaptchaResponse + '\'' +
                '}';
    }
}
