package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.Photo;
import com.pany.adv.advtask.repository.PhotoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PhotoService {

    private final
    PhotoRep photoRep;

    @Autowired
    public PhotoService(PhotoRep photoRep) {
        this.photoRep = photoRep;
    }

    public void createPhoto(Photo photo) {
        photoRep.save(photo);
    }

    public Photo findById(long id) {
        return photoRep.findOne(id);
    }

    public List<Photo> findAll() {
        return photoRep.findAll();
    }

    @Transactional
    public void updatePhoto(long id, Photo photo) {
        Photo targetPhoto = findById(id);
        targetPhoto.setAddress(photo.getAddress());
        targetPhoto.setRequest(photo.getRequest());
    }

    public void deletePhoto(long id) {
        photoRep.delete(id);
    }

}
