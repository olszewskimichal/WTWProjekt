package com.wtw.validator;

import com.wtw.form.UserCreateForm;
import com.wtw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Admin on 2016-03-01.
 */
@Component
public class UserCreateValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserCreateValidator(UserService userService) {
        this.userService = userService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserCreateForm.class);
    }

    public void validate(Object target, Errors errors) {
        UserCreateForm form = (UserCreateForm) target;
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            errors.rejectValue("password", "password_error");
        }

        if (userService.getUserByEmail(form.getEmail()).isPresent()) {
            errors.rejectValue("email", "email_error");
        }
    }
}
