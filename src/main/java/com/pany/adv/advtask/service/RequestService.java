package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.builders.RequestBuilder;
import com.pany.adv.advtask.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RequestService {

    private final RequestRep requestToAddAdConstr;

    private final UserRep userRep;

    private final AdvConstructionRep constructionRep;

    private final AdvPlaceRep placeRep;

    private final PhotoRep photoRep;

    @Autowired
    public RequestService(RequestRep requestToAddAdConstr, UserRep userRep, AdvConstructionRep constructionRep, AdvPlaceRep placeRep, PhotoRep photoRep) {
        this.requestToAddAdConstr = requestToAddAdConstr;
        this.userRep = userRep;
        this.constructionRep = constructionRep;
        this.placeRep = placeRep;
        this.photoRep = photoRep;
    }

    public void insertData() {
        requestToAddAdConstr.save(new RequestBuilder().withDate(new Date()).withApplicant(userRep.findOne(1L)).
                withStatus("BestStatus").withAdvPlace(placeRep.findOne(1L)).
                withAdvConstruction(constructionRep.findOne(1L)).withHandler(userRep.findOne(1L)).
                withDateProcessed(new Date()).withVersion("1.1").withReason("kek").withActuality("best").
                withPhoto(photoRep.findOne(1L)).build());
    }
}
