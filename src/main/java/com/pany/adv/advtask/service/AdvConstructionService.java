package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.AdvConstruction;
import com.pany.adv.advtask.exceptions.EntitiesNotFoundException;
import com.pany.adv.advtask.exceptions.MissingParametersException;
import com.pany.adv.advtask.exceptions.ResourceNotFound;
import com.pany.adv.advtask.repository.AdvConstructionRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AdvConstructionService {

    private final
    AdvConstructionRep constructionRep;

    private final AdvPlaceService advPlaceService;

    @Autowired
    public AdvConstructionService(AdvConstructionRep constructionRep, AdvPlaceService advPlaceService) {
        this.constructionRep = constructionRep;
        this.advPlaceService = advPlaceService;
    }

    //-------------------------------------CRUD-------------------------------------

    public void createConstruction(AdvConstruction construction) {
        if (construction == null) {
            throw new MissingParametersException();
        }
        constructionRep.save(construction);
    }

    public AdvConstruction findById(long id) {
        AdvConstruction construction = constructionRep.findOne(id);
        if (construction == null) {
            throw new ResourceNotFound();
        }
        return construction;
    }

    public List<AdvConstruction> findAll() {
        List<AdvConstruction> constructions = constructionRep.findAll();
        if (constructions.isEmpty()) {
            throw new EntitiesNotFoundException();
        }
        return constructions;
    }

    @Transactional
    public void updateConstruction(long id, AdvConstruction construction) {
        if (construction == null) {
            throw new MissingParametersException();
        }
        AdvConstruction advConstruction = findById(id);
        if (advConstruction == null) {
            throw new ResourceNotFound();
        }
        advConstruction.setAdvPlaceId(construction.getAdvPlaceId());
        advConstruction.setDate(construction.getDate());
        advConstruction.setNumber(construction.getNumber());
        advConstruction.setOwner(construction.getOwner());
        advConstruction.setStatus(construction.getStatus());
        advConstruction.setType(construction.getType());
    }

    public void deleteConstruction(long id) {
        AdvConstruction advConstruction = findById(id);
        if (advConstruction == null) {
            throw new ResourceNotFound();
        }
        constructionRep.delete(advConstruction);
    }

    //-----------------------------------JUST_TEST----------------------------------

    public void insertData() {
        constructionRep.save(new AdvConstruction(advPlaceService.findById(1L), "owner", 1, "type",
                "status", new Date()));
    }

}
