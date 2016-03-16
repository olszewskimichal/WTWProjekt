package com.wtw.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class ContactForm extends RecaptchaForm {
    @Size(min = 4, max = 20)
    private String name;

    @Size(min = 4, max = 30)
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String message;

    public ContactForm(String name, String lastName, String email, String message) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.message = message;
    }

    public ContactForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ContactForm{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                '}' + super.toString();
    }
}
