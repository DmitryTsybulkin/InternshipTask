package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.Request;
import com.pany.adv.advtask.repository.RequestRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequestController {

    private final RequestRep requestRep;

    @Autowired
    public RequestController(RequestRep requestRep) {
        this.requestRep = requestRep;
    }

    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public List<Request> showRequests() {
        return requestRep.findAll();
    }

}
