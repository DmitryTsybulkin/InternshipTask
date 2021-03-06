package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.Photo;
import com.pany.adv.advtask.domain.Request;
import com.pany.adv.advtask.dto.PhotoDTO;
import com.pany.adv.advtask.service.PhotoService;
import com.pany.adv.advtask.service.convertors.PhotoDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class PhotoController {

    private final PhotoService service;

    private final PhotoDTOConverter converter;

    @Autowired
    public PhotoController(PhotoService service, PhotoDTOConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @PostMapping(value = "/photos")
    public PhotoDTO createPhoto(@RequestParam MultipartFile file,
                                @RequestBody Request request) throws IOException {
        Photo photo = service.createPhoto(file, request);
        return converter.toDto(photo);
    }

    @GetMapping(value = "/photos")
    public List<PhotoDTO> showPhotos() {
        return converter.toDtoList(service.findAll());
    }

    //get image file on frontend by absolute address
    @GetMapping(value = "/photos/{id}")
    public PhotoDTO showPhoto(@PathVariable("id") long id) throws IOException {
        return converter.toDto(service.findById(id));
    }

    @GetMapping(value = "/photos/{id}/{fileName}")
    public byte[] getPhoto(@RequestParam("fileName") String fileName) throws IOException {
        return service.getPhoto(fileName);
    }

}
