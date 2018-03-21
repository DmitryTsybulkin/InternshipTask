package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.AdvConstruction;
import com.pany.adv.advtask.dto.AdvConstructionDTO;
import com.pany.adv.advtask.service.AdvConstructionService;
import com.pany.adv.advtask.service.convertors.AdvConstructionDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdvConstructionController {

    private final AdvConstructionService service;

    private final AdvConstructionDTOConverter converter;

    @Autowired
    public AdvConstructionController(AdvConstructionService service, AdvConstructionDTOConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @PostMapping(value = "/constructions")
    public AdvConstructionDTO createConstruction(@RequestBody AdvConstruction construction) {
        service.createConstruction(construction);
        return converter.toDto(construction);
    }

    @GetMapping(value = "/constructions")
    public List<AdvConstructionDTO> showConstructions() {
        List<AdvConstruction> constructions = service.findAll();
        List<AdvConstructionDTO> advConstructionDTOS = new ArrayList<>();
        for (AdvConstruction construction : constructions) {
            advConstructionDTOS.add(converter.toDto(construction));
        }
        return advConstructionDTOS;
    }

    @GetMapping(value = "/constructions/{id}")
    public AdvConstructionDTO showConstruction(@PathVariable("id") long id) {
        return converter.toDto(service.findById(id));
    }

    @PutMapping(value = "/constructions/{id}")
    public AdvConstructionDTO updateConstruction(@RequestBody AdvConstruction construction, @PathVariable("id") long id) {
        service.updateConstruction(id, construction);
        return converter.toDto(construction);
    }

    @DeleteMapping(value = "/constructions/{id}")
    public void deleteConstruction(@PathVariable("id") long id) {
        service.deleteConstruction(id);
    }

}
