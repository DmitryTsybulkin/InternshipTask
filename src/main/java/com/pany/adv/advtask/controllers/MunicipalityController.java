package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.Municipality;
import com.pany.adv.advtask.repository.MunicipalityRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/municipalities", method = RequestMethod.GET)
    public List<Municipality> showAll() {
        return municipalityRep.findAll();
    }

}
