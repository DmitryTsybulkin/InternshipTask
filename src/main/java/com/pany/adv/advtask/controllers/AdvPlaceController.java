package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.AdvPlace;
import com.pany.adv.advtask.dto.AdvPlaceDTO;
import com.pany.adv.advtask.service.AdvPlaceService;
import com.pany.adv.advtask.service.convertors.AdvPlaceDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdvPlaceController {

    private final AdvPlaceService service;

    private final AdvPlaceDTOConverter converter;

    @Autowired
    public AdvPlaceController(AdvPlaceService service, AdvPlaceDTOConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @PostMapping(value = "/places")
    public AdvPlaceDTO createPlace(@RequestBody AdvPlace place) {
        service.createPlace(place);
        return converter.toDto(place);
    }

    @GetMapping(value = "/places")
    public List<AdvPlaceDTO> showPlaces() {
        List<AdvPlace> places = service.findAll();
        List<AdvPlaceDTO> placeDTOS = new ArrayList<>();
        for (AdvPlace place : places) {
            placeDTOS.add(converter.toDto(place));
        }
        return placeDTOS;
    }

    @GetMapping(value = "/places/{id}")
    public AdvPlaceDTO showPlace(@PathVariable("id") long id) {
        return converter.toDto(service.findById(id));
    }

    @PutMapping(value = "/places/{id}")
    public AdvPlaceDTO updatePlace(@RequestBody AdvPlace place, @RequestParam long id) {
        service.updateAdvPlace(id, place);
        return converter.toDto(place);
    }

    @DeleteMapping(value = "/places/{id}")
    public void deletePlace(@PathVariable("id") long id) {
        service.deleteAdvPlace(id);
    }

}
