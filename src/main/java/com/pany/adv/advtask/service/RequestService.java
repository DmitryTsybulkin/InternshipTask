package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.*;
import com.pany.adv.advtask.domain.builders.ArchiveBuilder;
import com.pany.adv.advtask.exceptions.EntitiesNotFoundException;
import com.pany.adv.advtask.exceptions.MissingParametersException;
import com.pany.adv.advtask.exceptions.ResourceNotFound;
import com.pany.adv.advtask.repository.*;
import com.pany.adv.advtask.service.utils.FileSaver;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

@Service
public class RequestService {

    private final RequestRep requestRep;

    private final PhotoRep photoRep;

    private final KieContainer kieContainer;

    private final FileSaver fileSaver;

    private final ArchiveRep archiveRep;

    @Autowired
    public RequestService(RequestRep requestRep, KieContainer kieContainer, FileSaver fileSaver, PhotoRep photoRep, ArchiveRep archiveRep) {
        this.requestRep = requestRep;
        this.kieContainer = kieContainer;
        this.fileSaver = fileSaver;
        this.photoRep = photoRep;
        this.archiveRep = archiveRep;
    }
    
    //-------------------------------------CRUD-------------------------------------
    
    public void createRequest(Request newRequest) {
        if (newRequest == null) {
            throw new MissingParametersException();
        }
        requestRep.save(newRequest);
    }

    public Request findById(long id) {
        Request request = requestRep.findOne(id);
        if (request == null) {
            throw new ResourceNotFound();
        }
        return request;
    }

    public List<Request> findAll() {
        List<Request> requests = requestRep.findAll();
        if (requests.isEmpty()) {
            throw new EntitiesNotFoundException();
        }
        return requests;
    }

    @Transactional
    public void updateRequest(long id, Request newRequest) {
        if (newRequest == null) {
            throw new MissingParametersException();
        }
        Request request = findById(id);
        if (request == null) {
            throw new ResourceNotFound();
        }
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

        if (newRequest.getStatus().equals("Отклонено")) {
            archiveRep.save(new ArchiveBuilder().withActuality(newRequest.getActuality()).withAdvConstruction(newRequest.getAdvConstruction())
                    .withAdvPlace(newRequest.getAdvPlace()).withApplicant(newRequest.getApplicant()).withDate(newRequest.getDate())
                    .withDateProcessed(newRequest.getDateProcessed()).withHandler(newRequest.getHandler()).withReason(newRequest.getReason())
                    .withRequestId(request).withVersion(newRequest.getVersion()).build());
        }

        KieSession kieSession = kieContainer.newKieSession("rulesSession");
        kieSession.insert(newRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    public void deleteRequest(long id) throws IOException {
        Request request = requestRep.findOne(id);
        if (request == null) {
            throw new ResourceNotFound();
        }
        deletePhoto(request);
        requestRep.delete(request);
    }

    private void deletePhoto(Request request) throws IOException {

        boolean deleted;
        Photo targetPhoto = photoRep.findPhotoByRequest(request);

        if (targetPhoto == null) {
            throw new ResourceNotFound();
        }

        deleted = fileSaver.deleteFile(targetPhoto.getFileName());

        if (!deleted) {
            throw new FileNotFoundException();
        }

        photoRep.delete(targetPhoto);

    }
}
