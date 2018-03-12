package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.Municipality;
import com.pany.adv.advtask.dto.MunicipalityDTO;
import com.pany.adv.advtask.service.MunicipalityService;
import com.pany.adv.advtask.service.convertors.MunicipalityDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MunicipalityController {

    private final MunicipalityService municipalityService;

    private final MunicipalityDTOConverter converter;

    @Autowired
    public MunicipalityController(MunicipalityService municipalityService, MunicipalityDTOConverter converter) {
        this.municipalityService = municipalityService;
        this.converter = converter;
    }

    @PostMapping(value = "/municipalities")
    public MunicipalityDTO createMunicipality(@RequestBody Municipality municipality) {
        municipalityService.createMunicipality(municipality);
        return converter.toDto(municipality);
    }

    @GetMapping(value = "/municipalities")
    public List<MunicipalityDTO> showAll() {
        return converter.toDtoList(municipalityService.findAll());
    }

    @GetMapping(value = "/municipalities/{id}")
    public MunicipalityDTO showMunicipality(@PathVariable("id") long id) {
        return converter.toDto(municipalityService.findById(id));
    }

    @PutMapping(value = "/municipalities/{id}")
    public MunicipalityDTO updateMunicipality(@RequestBody Municipality municipality, @RequestParam long id) {
        municipalityService.update(id, municipality.getName());
        return converter.toDto(municipality);
    }

    @DeleteMapping(value = "/municipalities/{id}")
    public void deleteMunicipality(@PathVariable long id) {
        municipalityService.delete(id);
    }

}
