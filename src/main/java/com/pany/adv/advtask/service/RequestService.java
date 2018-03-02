package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.*;
import com.pany.adv.advtask.domain.builders.RequestBuilder;
import com.pany.adv.advtask.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Request> findByStatus(String status) {
        return request.findByStatus(status);
    }

    public List<Request> findByActuality(String actuality) {
        return request.findByActuality(actuality);
    }

    public List<Request> findByAdvConstruction_Id(long id) {
        return request.findByAdvConstruction_Id(id);
    }

    public List<Request> findByAdvPlace_Id(long id) {
        return request.findByAdvPlace_Id(id);
    }

    public List<Request> findByDate(Date date) {
        return request.findByDate(date);
    }

    public List<Request> findByPhoto(Photo photo) {
        return request.findByPhoto(photo);
    }

    public List<Request> findByHandler(User handler) {
        return request.findByHandler(handler);
    }

    public List<Request> findByApplicant(User applicant) {
        return request.findByApplicant(applicant);
    }

    public List<Request> findByDateProcessed(Date date) {
        return request.findByDateProcessed(date);
    }

    public void updateRequest(long id, Request newRequest) {
        this.findById(id).setActuality(newRequest.getActuality());
        this.findById(id).setAdvConstruction(newRequest.getAdvConstruction());
        this.findById(id).setAdvPlace(newRequest.getAdvPlace());
        this.findById(id).setApplicant(newRequest.getApplicant());
        this.findById(id).setDate(newRequest.getDate());
        this.findById(id).setDateProcessed(newRequest.getDateProcessed());
        this.findById(id).setHandler(newRequest.getHandler());
        this.findById(id).setReason(newRequest.getReason());
        this.findById(id).setStatus(newRequest.getStatus());
        this.findById(id).setVersion(newRequest.getVersion());
        this.findById(id).setPhoto(newRequest.getPhoto());
    }

    public void updateDate(long id, Date date) {
        this.findById(id).setDate(date);
    }

    public void updateActuality(long id, String actuality) {
        this.findById(id).setActuality(actuality);
    }
    
    public void updateAdvConstruction(long id, AdvConstruction construction) {
        this.findById(id).setAdvConstruction(construction);
    }
    
    public void updateApplicant(long id, User applicant) {
        this.findById(id).setApplicant(applicant);
    }

    public void updateAdvPlace(long id, AdvPlace place) {
        this.findById(id).setAdvPlace(place);
    }

    public void updateDateProcessed(long id, Date date) {
        this.findById(id).setDateProcessed(date);
    }

    public void updateHandler(long id, User handler) {
        this.findById(id).setHandler(handler);
    }

    public void updateReason(long id, String reason) {
        this.findById(id).setReason(reason);
    }

    public void updateStatus(long id, String status) {
        this.findById(id).setStatus(status);
    }

    public void updateVersion(long id, String version) {
        this.findById(id).setVersion(version);
    }

    public void updatePhoto(long id, Photo photo) {
        this.findById(id).setPhoto(photo);
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
