package com.pany.adv.advtask.service.convertors;

import com.pany.adv.advtask.domain.Municipality;
import com.pany.adv.advtask.dto.MunicipalityDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MunicipalityDTOConverter {

    public MunicipalityDTO toDto(Municipality municipality) {
        final MunicipalityDTO dto = new MunicipalityDTO();
        dto.id = municipality.getId();
        dto.name = municipality.getName();
        return dto;
    }

    public List<MunicipalityDTO> toDtoList(List<Municipality> municipalities) {
        final List<MunicipalityDTO> dtos = new ArrayList<>();
        for (Municipality municipality : municipalities) {
            dtos.add(toDto(municipality));
        }
        return dtos;
    }

}
