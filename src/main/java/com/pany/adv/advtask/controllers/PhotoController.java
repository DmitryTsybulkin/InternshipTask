package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.Photo;
import com.pany.adv.advtask.domain.Request;
import com.pany.adv.advtask.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class PhotoController {

    private final PhotoService service;

    @Autowired
    public PhotoController(PhotoService service) {
        this.service = service;
    }

    @PostMapping(value = "/photos")
    public Photo createPhoto(@RequestParam MultipartFile file, @RequestParam Request request) throws IOException {
        return service.createPhoto(file, request);
    }

    @GetMapping(value = "/photos")
    public List<Photo> showPhotos() {
        return service.findAll();
    }

    @GetMapping(value = "/photos/{id}")
    public Photo showPhoto(@PathVariable("id") long id) {
        return service.findById(id);
    }

    @PutMapping(value = "/photos/{id}")
    public Photo updatePhoto(@RequestParam long id, @RequestParam MultipartFile file, @RequestParam Request requestId) throws IOException {
        return service.updatePhoto(id, file, requestId);
    }

    @DeleteMapping(value = "/photos/{id}")
    public void deletePhoto(@PathVariable("id") long id) throws IOException {
        service.deletePhoto(id);
    }

}
