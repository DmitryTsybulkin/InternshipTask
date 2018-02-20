package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.User;
import com.pany.adv.advtask.repository.RequesToAddAdConstr;
import com.pany.adv.advtask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> showRequests() {
        return userRepository.findAll();
    }
}
