package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.Municipality;
import com.pany.adv.advtask.repository.MunicipalityRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/municipality")
public class MunicipalityController {

    private final MunicipalityRep municipalityRep;

    @Autowired
    public MunicipalityController(MunicipalityRep municipalityRep) {
        this.municipalityRep = municipalityRep;
    }


    @GetMapping()
    public void createMunicipality(Municipality municipality) {
        municipalityRep.save(municipality);
    }

    public List<Municipality> findAll() throws NullPointerException {
        return municipalityRep.findAll();
    }

    public Municipality findById(long id) throws NullPointerException {
        return municipalityRep.findOne(id);
    }

    public void update(long id, String newName) throws NullPointerException {
        Municipality oldMunicipality = municipalityRep.findOne(id);
        oldMunicipality.setName(newName);
    }

    public void delete(long id) {
        municipalityRep.delete(id);
    }

}
