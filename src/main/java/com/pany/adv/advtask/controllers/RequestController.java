package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.Request;
import com.pany.adv.advtask.repository.RequestRep;
import com.pany.adv.advtask.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping(value = "/requests")
    public Request createRequest(@RequestBody Request request) {
        requestService.createRequest(request);
        return request;
    }

    @GetMapping(value = "/requests")
    public List<Request> showRequests() {
        return requestService.findAll();
    }

    @GetMapping(value = "/requests/{id}")
    public Request showRequest(@PathVariable("id") long id) {
        return requestService.findById(id);
    }

    @PutMapping(value = "/requests/{id}")
    public Request updateRequest(@RequestBody Request request, @RequestParam long id) {
        requestService.updateRequest(id, request);
        return request;
    }

    @DeleteMapping(value = "/requests/{id}")
    public void deleteRequest(@PathVariable("id") long id) {
        requestService.deleteRequest(id);
    }

}
