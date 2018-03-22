package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.Request;
import com.pany.adv.advtask.dto.RequestDTO;
import com.pany.adv.advtask.repository.RequestRep;
import com.pany.adv.advtask.service.RequestService;
import com.pany.adv.advtask.service.convertors.RequestDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RequestController {

    private final RequestService requestService;

    private final RequestDTOConverter converter;

    @Autowired
    public RequestController(RequestService requestService, RequestDTOConverter converter) {
        this.requestService = requestService;
        this.converter = converter;
    }

    @PostMapping(value = "/requests")
    public RequestDTO createRequest(@RequestBody Request request) {
        requestService.createRequest(request);
        return converter.toDto(request);
    }

    @GetMapping(value = "/requests")
    public List<RequestDTO> showRequests() {
        List<Request> requests = requestService.findAll();
        List<RequestDTO> requestDTOS = new ArrayList<>();
        for (Request request : requests) {
            requestDTOS.add(converter.toDto(request));
        }
        return requestDTOS;
    }

    @GetMapping(value = "/requests/{id}")
    public RequestDTO showRequest(@PathVariable("id") long id) {
        return converter.toDto(requestService.findById(id));
    }

    @PutMapping(value = "/requests/{id}")
    public RequestDTO updateRequest(@PathVariable("id") long id, @RequestBody Request request) {
        requestService.updateRequest(id, request);
        return converter.toDto(request);
    }

    @DeleteMapping(value = "/requests/{id}")
    public void deleteRequest(@PathVariable("id") long id) throws IOException {
        requestService.deleteRequest(id);
    }

}
