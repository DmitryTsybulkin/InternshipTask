package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.AdvPlace;
import com.pany.adv.advtask.service.AdvPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdvPlaceController {

    private final AdvPlaceService service;

    @Autowired
    public AdvPlaceController(AdvPlaceService service) {
        this.service = service;
    }

    @PostMapping(value = "/places")
    public AdvPlace createPlace(@RequestBody AdvPlace place) {
        service.createPlace(place);
        return place;
    }

    @GetMapping(value = "/places")
    public List<AdvPlace> showPlaces() {
        return service.findAll();
    }

    @GetMapping(value = "/places/{id}")
    public AdvPlace showPlace(@PathVariable("id") long id) {
        return service.findById(id);
    }

    @PutMapping(value = "/places/{id}")
    public AdvPlace updatePlace(@RequestBody AdvPlace place, @RequestParam long id) {
        service.updateAdvPlace(id, place);
        return place;
    }

    @DeleteMapping(value = "/places/{id}")
    public void deletePlace(@PathVariable("id") long id) {
        service.deleteAdvPlace(id);
    }

}
