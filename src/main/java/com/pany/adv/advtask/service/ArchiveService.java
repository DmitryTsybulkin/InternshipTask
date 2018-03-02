package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.*;
import com.pany.adv.advtask.domain.Archive;
import com.pany.adv.advtask.repository.ArchiveRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArchiveService {

    private final
    ArchiveRep archiveRep;

    @Autowired
    public ArchiveService(ArchiveRep archiveRep) {
        this.archiveRep = archiveRep;
    }

    //-------------------------------------CRUD-------------------------------------

    public void createArchiveEntry(Archive archive) {
        archiveRep.save(archive);
    }

    public Archive findById(long id) {
        return archiveRep.findOne(id);
    }

    public List<Archive> findAll() {
        return archiveRep.findAll();
    }

    public List<Archive> findByActuality(String actuality) {
        return archiveRep.findByActuality(actuality);
    }

    public List<Archive> findByAdvConstruction_Id(long id) {
        return archiveRep.findByAdvConstruction_Id(id);
    }

    public List<Archive> findByAdvPlace_Id(long id) {
        return archiveRep.findByAdvPlace_Id(id);
    }

    public List<Archive> findByDate(Date date) {
        return archiveRep.findByDate(date);
    }

    public List<Archive> findByHandler(User handler) {
        return archiveRep.findByHandler(handler);
    }

    public List<Archive> findByApplicant(User applicant) {
        return archiveRep.findByApplicant(applicant);
    }

    public List<Archive> findByDateProcessed(Date date) {
        return archiveRep.findByDateProcessed(date);
    }

    public Archive findByRequestId(Request requestId) {
        return archiveRep.findByRequestId(requestId);
    }

    public void updateArchive(long id, Archive newArchive) {
        this.findById(id).setActuality(newArchive.getActuality());
        this.findById(id).setRequestId(newArchive.getRequestId());
        this.findById(id).setAdvConstruction(newArchive.getAdvConstruction());
        this.findById(id).setAdvPlace(newArchive.getAdvPlace());
        this.findById(id).setApplicant(newArchive.getApplicant());
        this.findById(id).setDate(newArchive.getDate());
        this.findById(id).setDateProcessed(newArchive.getDateProcessed());
        this.findById(id).setHandler(newArchive.getHandler());
        this.findById(id).setReason(newArchive.getReason());
        this.findById(id).setVersion(newArchive.getVersion());
    }

    public void updateDate(long id, Date date) {
        this.findById(id).setDate(date);
    }

    public void updateActuality(long id, String actuality) {
        this.findById(id).setActuality(actuality);
    }

    public void updateRequestId(long id, Request request) {
        this.findById(id).setRequestId(request);
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

    public void updateVersion(long id, String version) {
        this.findById(id).setVersion(version);
    }

    public void deleteArchiveEntry(long id) {
        archiveRep.delete(id);
    }

    //-----------------------------------JUST_TEST----------------------------------

}
