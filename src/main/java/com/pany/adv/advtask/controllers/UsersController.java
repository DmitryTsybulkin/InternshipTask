package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.User;
import com.pany.adv.advtask.repository.UserRep;
import com.pany.adv.advtask.service.SecurityEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {

    private final UserRep userRep;

    @Autowired
    public UsersController(UserRep userRep) {
        this.userRep = userRep;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> showUsers() {
        return userRep.findAll();
    }

}
