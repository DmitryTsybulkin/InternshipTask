package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.*;
import com.pany.adv.advtask.domain.builders.RequestBuilder;
import com.pany.adv.advtask.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RequestService {

    private final RequestRep request;

    private final UserRep userRep;

    private final AdvConstructionRep constructionRep;

    private final AdvPlaceRep placeRep;

    private final PhotoRep photoRep;

    @Autowired
    public RequestService(RequestRep request, UserRep userRep, AdvConstructionRep constructionRep, AdvPlaceRep placeRep, PhotoRep photoRep) {
        this.request = request;
        this.userRep = userRep;
        this.constructionRep = constructionRep;
        this.placeRep = placeRep;
        this.photoRep = photoRep;
    }
    
    //-------------------------------------CRUD-------------------------------------
    
    public void createRequest(Request newRequest) {
        request.save(newRequest);
    }

    public Request findById(long id) {
        return request.findOne(id);
    }

    public List<Request> findAll() {
        return request.findAll();
    }

    @Transactional
    public void updateRequest(long id, Request newRequest) {
        Request request = findById(id);
        request.setActuality(newRequest.getActuality());
        request.setAdvConstruction(newRequest.getAdvConstruction());
        request.setAdvPlace(newRequest.getAdvPlace());
        request.setApplicant(newRequest.getApplicant());
        request.setDate(newRequest.getDate());
        request.setDateProcessed(newRequest.getDateProcessed());
        request.setHandler(newRequest.getHandler());
        request.setReason(newRequest.getReason());
        request.setStatus(newRequest.getStatus());
        request.setVersion(newRequest.getVersion());
        request.setPhoto(newRequest.getPhoto());
    }

    public void deleteRequest(long id) {
        request.delete(id);
    }
    
    //-------------------------------------JUST_TEST-------------------------------------

    public void insertData() {
        request.save(new RequestBuilder().withDate(new Date()).withApplicant(userRep.findOne(1L)).
                withStatus("BestStatus").withAdvPlace(placeRep.findOne(1L)).
                withAdvConstruction(constructionRep.findOne(1L)).withHandler(userRep.findOne(1L)).
                withDateProcessed(new Date()).withVersion("1.1").withReason("kek").withActuality("best").
                withPhoto(photoRep.findOne(1L)).build());
    }
}
