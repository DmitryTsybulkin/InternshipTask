package com.pany.adv.advtask.service.convertors;

import com.pany.adv.advtask.domain.Request;
import com.pany.adv.advtask.dto.RequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestDTOConverter {

    private AdvConstructionDTOConverter constructionDTOConverter;

    private AdvPlaceDTOConverter advPlaceDTOConverter;

    private PhotoDTOConverter photoDTOConverter;

    public RequestDTO toDto(Request request) {
        final RequestDTO dto = new RequestDTO();
        dto.id = request.getId();
        dto.actuality = request.getActuality();
        dto.advConstruction = constructionDTOConverter.toDto(request.getAdvConstruction());
        dto.applicant = request.getApplicant().getLogin();
        dto.date = request.getDate();
        dto.dateProcessed = request.getDateProcessed();
        dto.handler = request.getHandler().getLogin();
        dto.place = advPlaceDTOConverter.toDto(request.getAdvPlace());
        dto.photo = photoDTOConverter.toDto(request.getPhoto());
        dto.reason = request.getReason();
        dto.status = request.getStatus();
        dto.version = request.getVersion();
        return dto;
    }


    @Autowired
    public void setConstructionDTOConverter(AdvConstructionDTOConverter constructionDTOConverter) {
        this.constructionDTOConverter = constructionDTOConverter;
    }
    @Autowired
    public void setAdvPlaceDTOConverter(AdvPlaceDTOConverter advPlaceDTOConverter) {
        this.advPlaceDTOConverter = advPlaceDTOConverter;
    }
    @Autowired
    public void setPhotoDTOConverter(PhotoDTOConverter photoDTOConverter) {
        this.photoDTOConverter = photoDTOConverter;
    }
}
