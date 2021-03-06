package com.pany.adv.advtask.service.convertors;

import com.pany.adv.advtask.domain.Photo;
import com.pany.adv.advtask.dto.PhotoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoDTOConverter {

    public PhotoDTO toDto(Photo photo) {
        final PhotoDTO dto = new PhotoDTO();
        dto.id = photo.getId();
        dto.fileName = photo.getFileName();
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
