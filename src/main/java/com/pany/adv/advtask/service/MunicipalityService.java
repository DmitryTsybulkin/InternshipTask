package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.Municipality;
import com.pany.adv.advtask.repository.MunicipalityRep;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MunicipalityService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MunicipalityService.class);

    private final MunicipalityRep municipalityRep;

    @Autowired
    public MunicipalityService(MunicipalityRep municipalityRep) {
        this.municipalityRep = municipalityRep;
    }

    public void createMunicipality(Municipality municipality) {
        municipalityRep.save(municipality);
    }

    public List<Municipality> findAll() {
        return municipalityRep.findAll();
    }

    public Municipality findById(long id) {
        return municipalityRep.findOne(id);
    }

    @Transactional
    public void update(long id, String newName) {
        Municipality oldMunicipality = municipalityRep.findOne(id);
        oldMunicipality.setName(newName);
    }

    public void delete(long id) {
        municipalityRep.delete(id);
    }

    // test
    public void insertData() {
        municipalityRep.save(new Municipality("bestMunicipality"));
    }

}
