package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.Archive;
import com.pany.adv.advtask.exceptions.DuplicateEntityException;
import com.pany.adv.advtask.exceptions.EntitiesNotFoundException;
import com.pany.adv.advtask.exceptions.MissingParametersException;
import com.pany.adv.advtask.exceptions.ResourceNotFound;
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
        if (archive == null) {
            throw new MissingParametersException();
        }
        archiveRep.save(archive);
    }

    public Archive findById(long id) {
        Archive archive = archiveRep.findOne(id);
        if (archive == null) {
            throw new ResourceNotFound();
        }
        return archive;
    }

    public List<Archive> findAll() {
        List<Archive> archives = archiveRep.findAll();
        if (archives.isEmpty()) {
            throw new EntitiesNotFoundException();
        }
        return archives;
    }

    @Transactional
    public void updateArchive(long id, Archive newArchive) {
        if (newArchive == null) {
            throw new MissingParametersException();
        }
        Archive archive = findById(id);
        if (archive == null) {
            throw new ResourceNotFound();
        }
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
        Archive archive = findById(id);
        if (archive == null) {
            throw new ResourceNotFound();
        }
        archiveRep.delete(archive);
    }

    //-----------------------------------JUST_TEST----------------------------------

}
