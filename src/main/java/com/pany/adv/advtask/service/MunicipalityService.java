package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.Municipality;
import com.pany.adv.advtask.repository.MunicipalityRep;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipalityService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MunicipalityService.class);

    private final MunicipalityRep municipalityRep;

    @Autowired
    public MunicipalityService(MunicipalityRep municipalityRep) {
        this.municipalityRep = municipalityRep;
    }

    // test
    public void insertData() {
        municipalityRep.save(new Municipality("bestMunicipality"));
    }

}
