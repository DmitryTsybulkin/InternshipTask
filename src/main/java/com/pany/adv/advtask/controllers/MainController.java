package com.pany.adv.advtask.controllers;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
