package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.ConstructionRequest;
import com.pany.adv.advtask.domain.User;
import com.pany.adv.advtask.repository.ConstructionRequestRep;
import com.pany.adv.advtask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users_list";
    }

//    @RequestMapping(value = "/users", method = RequestMethod.GET)
//    public List<User> showUsers() {
//        return userRepository.findAll();
//    }

}
