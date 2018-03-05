package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.Photo;
import com.pany.adv.advtask.domain.Request;
import com.pany.adv.advtask.exceptions.APIException;
import com.pany.adv.advtask.repository.PhotoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PhotoService {

    private final PhotoRep photoRep;

    @Value("${upload.path}")
    String path;

    @Autowired
    public PhotoService(PhotoRep photoRep) {
        this.photoRep = photoRep;
    }

    public Photo createPhoto(MultipartFile file, Request requestId) throws IOException {
        String url;
        if (!file.isEmpty()) {
            final FileSaver fileSaver = new FileSaver();
            url = fileSaver.store(file);
        } else {
            throw new APIException("File with name: " + file.getOriginalFilename() + " doesn't exist.");
        }
        Photo photo = new Photo(requestId, url);
        photoRep.save(photo);
        return photo;
    }

    public Photo findById(long id) {
        return photoRep.findOne(id);
    }

    public List<Photo> findAll() {
        return photoRep.findAll();
    }

    @Transactional
    public Photo updatePhoto(long id, MultipartFile file, Request requestId) throws IOException {

        Photo targetPhoto = findById(id);
        boolean deleted;

        if (targetPhoto == null) {
            throw new APIException("Photo with id: " + id + " doesn't exist.");
        }

        if (file.isEmpty() || requestId == null) {
            throw new APIException("File or request id is empty.");
        }

        final FileSaver fileSaver = new FileSaver();
        deleted = fileSaver.deleteFile(targetPhoto.getAddress());

        if (!deleted) {
            throw new APIException("File: " + file.getOriginalFilename() + " doesn't exist.");
        }

        String address = fileSaver.store(file);
        targetPhoto.setRequest(requestId);
        targetPhoto.setAddress(address);
        return targetPhoto;
    }

    public void deletePhoto(long id) throws IOException {

        boolean deleted;

        if (photoRep.findOne(id) == null) {
            throw new APIException("Photo with id: " + id + " doesn't exist.");
        }

        String address = findById(id).getAddress();

        final FileSaver fileSaver = new FileSaver();
        deleted = fileSaver.deleteFile(address);

        if (!deleted) {
            throw new APIException("File by address: " + address + " doesn't exist.");
        }

        photoRep.delete(id);
    }

}
