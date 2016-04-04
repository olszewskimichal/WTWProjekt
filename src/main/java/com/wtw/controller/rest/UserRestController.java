package com.wtw.controller.rest;

import com.wtw.domain.User;
import com.wtw.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Admin on 2016-03-08.
 */
@RestController
@RequestMapping(value = "/api/users")
public class UserRestController {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/allUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return (List<User>) userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable("id") long userId) {
        return userService.getUserById(userId).
                orElseThrow(() -> new NoSuchElementException(String.format("Uzytkownik o id =%s nie istnieje", userId)));
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        logger.info("Zaktualizuj usera o id " + id + " " + user.toString());
        userService.updateUser(id, user);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addUser(@RequestBody User user) {
        logger.info("Dodaj usera " + user.toString());
        userService.createUser(user);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        logger.info("Usun usera o id " + id);
        userService.deleteUser(id);
    }
}
