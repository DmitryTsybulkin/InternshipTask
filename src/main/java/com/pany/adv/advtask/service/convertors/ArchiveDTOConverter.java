package com.pany.adv.advtask.service.convertors;

import com.pany.adv.advtask.domain.Archive;
import com.pany.adv.advtask.dto.ArchiveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchiveDTOConverter {

    private AdvConstructionDTOConverter advConstructionDTOConverter;

    private AdvPlaceDTOConverter advPlaceDTOConverter;

    private RequestDTOConverter requestDTOConverter;


    public ArchiveDTO toDto(Archive archive) {
        final ArchiveDTO dto = new ArchiveDTO();
        dto.id = archive.getId();
        dto.requestId = requestDTOConverter.toDto(archive.getRequestId());
        dto.actuality = archive.getActuality();
        dto.advConstruction = advConstructionDTOConverter.toDto(archive.getAdvConstruction());
        dto.applicant = archive.getApplicant().getLogin();
        dto.date = archive.getDate();
        dto.dateProcessed = archive.getDateProcessed();
        dto.handler = archive.getHandler().getLogin();
        dto.place = advPlaceDTOConverter.toDto(archive.getAdvPlace());
        dto.reason = archive.getReason();
        dto.version = archive.getVersion();
        return dto;
    }

    @Autowired
    public void setAdvConstructionDTOConverter(AdvConstructionDTOConverter advConstructionDTOConverter) {
        this.advConstructionDTOConverter = advConstructionDTOConverter;
    }
    @Autowired
    public void setAdvPlaceDTOConverter(AdvPlaceDTOConverter advPlaceDTOConverter) {
        this.advPlaceDTOConverter = advPlaceDTOConverter;
    }
    @Autowired
    public void setRequestDTOConverter(RequestDTOConverter requestDTOConverter) {
        this.requestDTOConverter = requestDTOConverter;
    }
}
