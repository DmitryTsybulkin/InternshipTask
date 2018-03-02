package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.Photo;
import com.pany.adv.advtask.domain.Request;
import com.pany.adv.advtask.repository.PhotoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Photo findByAddress(String address) {
        return photoRep.findByAddress(address);
    }

    public Photo findByRequestId(long request_id) {
        return photoRep.findByRequestId(request_id);
    }

    public void updatePhoto(long id, Photo photo) {
        this.findById(id).setAddress(photo.getAddress());
        this.findById(id).setRequest(photo.getRequest());
    }

    public void updateAddress(long id, String address) {
        this.findById(id).setAddress(address);
    }

    public void updateRequest(long id, Request request) {
        this.findById(id).setRequest(request);
    }

    public void deletePhoto(long id) {
        photoRep.delete(id);
    }

}
