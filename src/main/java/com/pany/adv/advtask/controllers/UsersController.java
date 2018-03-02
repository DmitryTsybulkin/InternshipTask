package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.User;
import com.pany.adv.advtask.repository.UserRep;
import com.pany.adv.advtask.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    private final UserRep userRep;

    private final UserService userService;

    @Autowired
    public UsersController(UserRep userRep, UserService userService) {
        this.userRep = userRep;
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> showUsers() {
        return userRep.findAll();
    }

    @RequestMapping(value = "/users/{login}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByLogin(@PathVariable("login") String login) {
        User user = userService.findByLogin(login);
        if (user == null) {
            log.info("user not found.");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        log.info("user was found!");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
