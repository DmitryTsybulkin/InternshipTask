package com.pany.adv.advtask.service.convertors;

import com.pany.adv.advtask.domain.Photo;
import com.pany.adv.advtask.dto.PhotoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoDTOConverter {

    private final RequestDTOConverter converter;

    @Autowired
    public PhotoDTOConverter(RequestDTOConverter converter) {
        this.converter = converter;
    }

    public PhotoDTO toDto(Photo photo) {
        final PhotoDTO dto = new PhotoDTO();
        dto.id = photo.getId();
        dto.requestDTO = converter.toDto(photo.getRequest());
        dto.address = photo.getAddress();
        return dto;
    }

    public List<PhotoDTO> toDtoList(List<Photo> photos) {
        final List<PhotoDTO> photoDTOS = new ArrayList<>();
        for (Photo photo : photos) {
            photoDTOS.add(toDto(photo));
        }
        return photoDTOS;
    }

}
