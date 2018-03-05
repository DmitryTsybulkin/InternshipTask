package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.AdvConstruction;
import com.pany.adv.advtask.service.AdvConstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdvConstructionController {

    private final AdvConstructionService service;

    @Autowired
    public AdvConstructionController(AdvConstructionService service) {
        this.service = service;
    }

    @PostMapping(value = "/constructions")
    public AdvConstruction createConstruction(@RequestBody AdvConstruction construction) {
        service.createConstruction(construction);
        return construction;
    }

    @GetMapping(value = "/constructions")
    public List<AdvConstruction> showConstructions() {
        return service.findAll();
    }

    @GetMapping(value = "/constructions/{id}")
    public AdvConstruction showConstruction(@PathVariable("id") long id) {
        return service.findById(id);
    }

    @PutMapping(value = "/constructions/{id}")
    public AdvConstruction updateConstruction(@RequestBody AdvConstruction construction, @RequestParam long id) {
        service.updateConstruction(id, construction);
        return construction;
    }

    @DeleteMapping(value = "/constructions/{id}")
    public void deleteConstruction(@PathVariable("id") long id) {
        service.deleteConstruction(id);
    }

}
