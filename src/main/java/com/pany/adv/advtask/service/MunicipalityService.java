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

    public void createMunicipality(Municipality municipality) {
        municipalityRep.save(municipality);
    }

    public List<Municipality> findAll() throws NullPointerException {
        return municipalityRep.findAll();
    }

    public Municipality findById(long id) throws NullPointerException {
        return municipalityRep.findOne(id);
    }

    public Municipality findByName(String name) throws NullPointerException {
        return municipalityRep.findByName(name);
    }

    public void update(long id, String newName) throws NullPointerException {
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
