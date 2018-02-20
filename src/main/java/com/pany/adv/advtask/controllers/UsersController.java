package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.ConstructionRequest;
import com.pany.adv.advtask.domain.User;
import com.pany.adv.advtask.repository.ConstructionRequestRep;
import com.pany.adv.advtask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConstructionRequestRep constructionRequestRep;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> showUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/req", method = RequestMethod.GET)
    public List<ConstructionRequest> showRequests() {
        return constructionRequestRep.findAll();
    }

}
