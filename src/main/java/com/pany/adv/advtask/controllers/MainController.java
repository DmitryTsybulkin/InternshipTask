package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.exceptions.ResourceNotFound;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String mainController() {
        return "Hello!";
    }

}
