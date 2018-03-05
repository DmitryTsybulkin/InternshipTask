package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.AdvPlace;
import com.pany.adv.advtask.repository.AdvPlaceRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdvPlaceService {

    private final
    AdvPlaceRep placeRep;

    @Autowired
    public AdvPlaceService(AdvPlaceRep placeRep) {
        this.placeRep = placeRep;
    }

    //---------------------------------------CRUD----------------------------------------

    public void createPlace(AdvPlace place) {
        placeRep.save(place);
    }

    public List<AdvPlace> findAll() {
        return placeRep.findAll();
    }

    public AdvPlace findById(long id) {
        return placeRep.findOne(id);
    }

    @Transactional
    public void updateAdvPlace(long id, AdvPlace place) {
        AdvPlace advPlace = findById(id);
        advPlace.setOwner(place.getOwner());
        advPlace.setAddress(place.getAddress());
        advPlace.setMunicipality(place.getMunicipality());
    }

    public void deleteAdvPlace(long id) {
        placeRep.delete(id);
    }

}
