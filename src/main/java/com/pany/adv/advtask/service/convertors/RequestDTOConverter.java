package com.pany.adv.advtask.service.convertors;

import com.pany.adv.advtask.domain.Photo;
import com.pany.adv.advtask.domain.Request;
import com.pany.adv.advtask.domain.User;
import com.pany.adv.advtask.dto.RequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Service
public class RequestDTOConverter {

    public RequestDTO toDto(Request request) {
        final RequestDTO dto = new RequestDTO();
        dto.id = request.getId();
        dto.actuality = request.getActuality();
        dto.advConstruction = request.getAdvConstruction().getId();
        dto.applicant = request.getApplicant().getLogin();
        dto.date = request.getDate();

        Date date = request.getDateProcessed();
        if (date == null) {
            dto.dateProcessed = new Date();
        } else {
            dto.dateProcessed = date;
        }

        User hand = request.getHandler();
        if (hand == null) {
            dto.handler = "";
        } else {
            dto.handler = hand.getLogin();
        }

        dto.place = request.getAdvPlace().getId();
        Photo photo = request.getPhoto();
        if (photo == null) {
            dto.photoName = "";
        } else {
            dto.photoName = photo.getFileName();
        }

        String reason = request.getReason();
        if (reason == null) {
            dto.reason = "";
        } else {
            dto.reason = reason;
        }

        String st = request.getStatus();
        if (st == null) {
            dto.status = "";
        } else {
            dto.status = st;
        }
        dto.version = request.getVersion();
        return dto;
    }

}
