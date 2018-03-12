package com.pany.adv.advtask.service.convertors;

import com.pany.adv.advtask.domain.AdvPlace;
import com.pany.adv.advtask.dto.AdvPlaceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvPlaceDTOConverter {

    private final MunicipalityDTOConverter converter;

    @Autowired
    public AdvPlaceDTOConverter(MunicipalityDTOConverter converter) {
        this.converter = converter;
    }

    public AdvPlaceDTO toDto(AdvPlace place) {
        final AdvPlaceDTO dto = new AdvPlaceDTO();
        dto.id = place.getId();
        dto.address = place.getAddress();
        dto.municipalityDTO = converter.toDto(place.getMunicipality());
        dto.owner = place.getOwner();
        return dto;
    }

}
