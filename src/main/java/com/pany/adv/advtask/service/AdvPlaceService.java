package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.AdvPlace;
import com.pany.adv.advtask.exceptions.DuplicateEntityException;
import com.pany.adv.advtask.exceptions.EntitiesNotFoundException;
import com.pany.adv.advtask.exceptions.MissingParametersException;
import com.pany.adv.advtask.exceptions.ResourceNotFound;
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
        if (place == null) {
            throw new MissingParametersException();
        }
        if (placeRep.findAll().contains(place)) {
            throw new DuplicateEntityException();
        }
        placeRep.save(place);
    }

    public List<AdvPlace> findAll() {
        List<AdvPlace> places = placeRep.findAll();
        if (places.isEmpty()) {
            throw new EntitiesNotFoundException();
        }
        return places;
    }

    public AdvPlace findById(long id) {
        AdvPlace place = placeRep.findOne(id);
        if (place == null) {
            throw new ResourceNotFound();
        }
        return place;
    }

    @Transactional
    public void updateAdvPlace(long id, AdvPlace place) {
        if (place == null) {
            throw new MissingParametersException();
        }
        AdvPlace advPlace = findById(id);
        if (advPlace == null) {
            throw new ResourceNotFound();
        }
        advPlace.setOwner(place.getOwner());
        advPlace.setAddress(place.getAddress());
        advPlace.setMunicipality(place.getMunicipality());
    }

    public void deleteAdvPlace(long id) {
        AdvPlace place = placeRep.findOne(id);
        if (place == null) {
            throw new ResourceNotFound();
        }
        placeRep.delete(place);
    }

}
