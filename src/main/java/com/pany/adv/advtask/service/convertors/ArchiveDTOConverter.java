package com.pany.adv.advtask.service.convertors;

import com.pany.adv.advtask.domain.Archive;
import com.pany.adv.advtask.dto.ArchiveDTO;
import org.springframework.stereotype.Service;

@Service
public class ArchiveDTOConverter {

    public ArchiveDTO toDto(Archive archive) {
        final ArchiveDTO dto = new ArchiveDTO();
        dto.id = archive.getId();
        dto.requestId = archive.getRequestId();
        dto.actuality = archive.getActuality();
        dto.advConstruction = archive.getAdvConstruction();
        dto.applicant = archive.getApplicant().getLogin();
        dto.date = archive.getDate();
        dto.dateProcessed = archive.getDateProcessed();
        dto.handler = archive.getHandler().getLogin();
        dto.place = archive.getAdvPlace();
        dto.reason = archive.getReason();
        dto.version = archive.getVersion();
        return dto;
    }
    
}
