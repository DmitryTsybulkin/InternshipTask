package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.Archive;
import com.pany.adv.advtask.repository.ArchiveRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void updateArchive(long id, Archive newArchive) {
        Archive archive = findById(id);
        archive.setActuality(newArchive.getActuality());
        archive.setRequestId(newArchive.getRequestId());
        archive.setAdvConstruction(newArchive.getAdvConstruction());
        archive.setAdvPlace(newArchive.getAdvPlace());
        archive.setApplicant(newArchive.getApplicant());
        archive.setDate(newArchive.getDate());
        archive.setDateProcessed(newArchive.getDateProcessed());
        archive.setHandler(newArchive.getHandler());
        archive.setReason(newArchive.getReason());
        archive.setVersion(newArchive.getVersion());
    }

    public void deleteArchiveEntry(long id) {
        archiveRep.delete(id);
    }

    //-----------------------------------JUST_TEST----------------------------------

}
