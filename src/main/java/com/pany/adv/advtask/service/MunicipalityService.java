package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.Municipality;
import com.pany.adv.advtask.exceptions.DuplicateEntityException;
import com.pany.adv.advtask.exceptions.EntitiesNotFoundException;
import com.pany.adv.advtask.exceptions.MissingParametersException;
import com.pany.adv.advtask.exceptions.ResourceNotFound;
import com.pany.adv.advtask.repository.MunicipalityRep;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MunicipalityService {

    private final MunicipalityRep municipalityRep;

    @Autowired
    public MunicipalityService(MunicipalityRep municipalityRep) {
        this.municipalityRep = municipalityRep;
    }

    public void createMunicipality(Municipality municipality) {
        if (municipality == null) {
            throw new MissingParametersException();
        }
        if (municipalityRep.countMunicipalitiesByName(municipality.getName()) > 0) {
            throw new DuplicateEntityException();
        }
        municipalityRep.save(municipality);
    }

    public List<Municipality> findAll() {
        List<Municipality> municipalities = municipalityRep.findAll();
        if (municipalities.isEmpty()) {
            throw new EntitiesNotFoundException();
        }
        return municipalities;
    }

    public Municipality findById(long id) {
        Municipality municipality = municipalityRep.findOne(id);
        if (municipality == null) {
            throw new ResourceNotFound();
        }
        return municipality;
    }

    @Transactional
    public void update(long id, String newName) {
        if (newName == null) {
            throw new MissingParametersException();
        }
        Municipality oldMunicipality = findById(id);
        if (oldMunicipality == null) {
            throw new ResourceNotFound();
        }
        oldMunicipality.setName(newName);
    }

    public void delete(long id) {
        Municipality municipality = municipalityRep.findOne(id);
        if (municipality == null) {
            throw new ResourceNotFound();
        }
        municipalityRep.delete(municipality);
    }

}
