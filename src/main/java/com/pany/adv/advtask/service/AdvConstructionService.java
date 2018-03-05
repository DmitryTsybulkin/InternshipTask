package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.AdvConstruction;
import com.pany.adv.advtask.repository.AdvConstructionRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdvConstructionService {

    private final
    AdvConstructionRep constructionRep;

    @Autowired
    public AdvConstructionService(AdvConstructionRep constructionRep) {
        this.constructionRep = constructionRep;
    }

    //-------------------------------------CRUD-------------------------------------

    public void createConstruction(AdvConstruction construction) {
        constructionRep.save(construction);
    }

    public AdvConstruction findById(long id) {
        return constructionRep.findOne(id);
    }

    public List<AdvConstruction> findAll() {
        return constructionRep.findAll();
    }

    @Transactional
    public void updateConstruction(long id, AdvConstruction construction) {
        AdvConstruction advConstruction = findById(id);
        advConstruction.setAdvPlaceId(construction.getAdvPlaceId());
        advConstruction.setDate(construction.getDate());
        advConstruction.setNumber(construction.getNumber());
        advConstruction.setOwner(construction.getOwner());
        advConstruction.setStatus(construction.getStatus());
        advConstruction.setType(construction.getType());
    }

    public void deleteConstruction(long id) {
        constructionRep.delete(id);
    }

    //-----------------------------------JUST_TEST----------------------------------

}
