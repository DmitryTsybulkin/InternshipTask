package com.pany.adv.advtask.service.convertors;

import com.pany.adv.advtask.domain.AdvConstruction;
import com.pany.adv.advtask.dto.AdvConstructionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvConstructionDTOConverter {

    private final
    AdvPlaceDTOConverter converter;

    @Autowired
    public AdvConstructionDTOConverter(AdvPlaceDTOConverter converter) {
        this.converter = converter;
    }

    public AdvConstructionDTO toDto(AdvConstruction construction) {
        final AdvConstructionDTO dto = new AdvConstructionDTO();
        dto.id = construction.getId();
        dto.date = construction.getDate();
        dto.number = construction.getNumber();
        dto.owner = construction.getOwner();
        dto.place = converter.toDto(construction.getAdvPlaceId());
        dto.type = construction.getType();
        dto.status = construction.getStatus();
        return dto;
    }

}
