package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.Municipality;
import com.pany.adv.advtask.repository.MunicipalityRep;
import com.pany.adv.advtask.service.MunicipalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MunicipalityController {

    private final MunicipalityService municipalityService;

    @Autowired
    public MunicipalityController(MunicipalityService municipalityService) {
        this.municipalityService = municipalityService;
    }

    @PostMapping(value = "/municipalities")
    public Municipality createMunicipality(@RequestBody Municipality municipality) {
        municipalityService.createMunicipality(municipality);
        return municipality;
    }

    @GetMapping(value = "/municipalities")
    public List<Municipality> showAll() {
        return municipalityService.findAll();
    }

    @GetMapping(value = "/municipalities/{id}")
    public Municipality showMunicipality(@PathVariable("id") long id) {
        return municipalityService.findById(id);
    }

    @PutMapping(value = "/municipalities/{id}")
    public Municipality updateMunicipality(@RequestBody Municipality municipality, @RequestParam long id) {
        municipalityService.update(id, municipality.getName());
        return municipality;
    }

    @DeleteMapping(value = "/municipalities/{id}")
    public void deleteMunicipality(@PathVariable long id) {
        municipalityService.delete(id);
    }

}
