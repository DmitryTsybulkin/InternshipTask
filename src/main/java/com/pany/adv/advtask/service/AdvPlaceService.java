package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.AdvPlace;
import com.pany.adv.advtask.domain.Municipality;
import com.pany.adv.advtask.repository.AdvPlaceRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public AdvPlace findByOwner(String owner) {
        return placeRep.findByOwner(owner);
    }

    public AdvPlace findByMunicipality(Municipality municipality) {
        return placeRep.findByMunicipality(municipality);
    }

    public AdvPlace findByAddress(String address) {
        return placeRep.findByAddress(address);
    }

    public void updateAdvPlace(long id, AdvPlace place) {
        this.findById(id).setOwner(place.getOwner());
        this.findById(id).setAddress(place.getAddress());
        this.findById(id).setMunicipality(place.getMunicipality());
    }

    public void updateOwner(long id, String owner) {
        this.findById(id).setOwner(owner);
    }

    public void updateAddress(long id, String address) {
        this.findById(id).setAddress(address);
    }

    public void updateMunicipality(long id, Municipality municipality) {
        this.findById(id).setMunicipality(municipality);
    }

    public void deleteAdvPlace(long id) {
        placeRep.delete(id);
    }

}
