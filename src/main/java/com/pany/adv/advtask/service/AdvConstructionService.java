package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.AdvConstruction;
import com.pany.adv.advtask.domain.AdvPlace;
import com.pany.adv.advtask.repository.AdvConstructionRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdvConstructionService {

    final
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

    public AdvConstruction findByAdvPlace_Id(long id) {
        return constructionRep.findByAdvPlace_Id(id);
    }

    public AdvConstruction findByStatus(String status) {
        return constructionRep.findByStatus(status);
    }

    public AdvConstruction findByOwner(String owner) {
        return constructionRep.findByOwner(owner);
    }

    public AdvConstruction findByDate(Date date) {
        return constructionRep.findByDate(date);
    }

    public AdvConstruction findByNumber(int number) {
        return constructionRep.findByNumber(number);
    }

    public AdvConstruction findByType(String type) {
        return constructionRep.findByType(type);
    }

    public List<AdvConstruction> findAll() {
        return constructionRep.findAll();
    }

    public void updateConstruction(long id, AdvConstruction construction) {
        this.findById(id).setAdvPlaceId(construction.getAdvPlaceId());
        this.findById(id).setDate(construction.getDate());
        this.findById(id).setNumber(construction.getNumber());
        this.findById(id).setOwner(construction.getOwner());
        this.findById(id).setStatus(construction.getStatus());
        this.findById(id).setType(construction.getType());
    }

    public void updateAdvPlaceId(long id, AdvPlace place) {
        this.findById(id).setAdvPlaceId(place);
    }

    public void updateDate(long id, Date date) {
        this.findById(id).setDate(date);
    }

    public void updateNumber(long id, int number) {
        this.findById(id).setNumber(number);
    }

    public void updateOwner(long id, String owner) {
        this.findById(id).setOwner(owner);
    }

    public void updateStatus(long id, String status) {
        this.findById(id).setStatus(status);
    }

    public void updateType(long id, String type) {
        this.findById(id).setType(type);
    }

    public void deleteConstruction(long id) {
        constructionRep.delete(id);
    }

    //-----------------------------------JUST_TEST----------------------------------

}
