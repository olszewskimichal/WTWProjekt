package com.wtw.service;

import com.wtw.domain.User;
import com.wtw.form.UserCreateForm;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Admin on 2016-03-01.
 */
public interface UserService {
    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

    //User editUser(UserEditForm userEditForm);

    User update(User user);
}
