package com.pany.adv.advtask.service.convertors;

import com.pany.adv.advtask.domain.Request;
import com.pany.adv.advtask.dto.RequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestDTOConverter {

    public RequestDTO toDto(Request request) {
        final RequestDTO dto = new RequestDTO();
        dto.id = request.getId();
        dto.actuality = request.getActuality();
        dto.advConstruction = request.getAdvConstruction().getId();
        dto.applicant = request.getApplicant().getLogin();
        dto.date = request.getDate();
        dto.dateProcessed = request.getDateProcessed();
        dto.handler = request.getHandler().getLogin();
        dto.place = request.getAdvPlace().getId();
        dto.photo = request.getPhoto().getId();
        dto.reason = request.getReason();
        dto.status = request.getStatus();
        dto.version = request.getVersion();
        return dto;
    }

}
