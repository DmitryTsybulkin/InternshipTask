package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.Photo;
import com.pany.adv.advtask.domain.Request;
import com.pany.adv.advtask.exceptions.*;
import com.pany.adv.advtask.repository.PhotoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class PhotoService {

    private final PhotoRep photoRep;

    private final FileSaver fileSaver;

    @Value("${upload.path}")
    String path;

    @Autowired
    public PhotoService(PhotoRep photoRep, FileSaver fileSaver) {
        this.photoRep = photoRep;
        this.fileSaver = fileSaver;
    }

    public Photo createPhoto(MultipartFile file, Request requestId) throws IOException {
        String url;

        if (file.isEmpty() || requestId == null) {
            throw new MissingParametersException();
        }

        final FileSaver fileSaver = new FileSaver();

        if (!fileSaver.isImage(file)) {
            throw new FileNotImageException();
        }

        if (fileSaver.isExists(file.getOriginalFilename())) {
            throw new DuplicateFileException();
        }

        if (photoRep.countPhotosByAddress(path + file.getOriginalFilename()) > 0) {
            throw new DuplicateEntityException();
        }

        url = fileSaver.store(file);
        Photo photo = new Photo(requestId, url);
        photoRep.save(photo);
        return photo;
    }

    public Photo findById(long id) throws IOException {
        Photo photo = photoRep.findOne(id);
        if (photo == null) {
            throw new ResourceNotFound();
        }
        return photo;
    }

    public List<Photo> findAll() {
        List<Photo> photos = photoRep.findAll();
        if (photos.isEmpty()) {
            throw new EntitiesNotFoundException();
        }
        return photos;
    }

    // TODO: 08.03.2018 get photo-file (frontend by address?)
    // TODO: 09.03.2018 check that all parameters is exists: frontend.

    @Transactional
    public Photo updatePhoto(long id, MultipartFile file, Request requestId) throws IOException {

        Photo targetPhoto = findById(id);

        if (targetPhoto == null) {
            throw new ResourceNotFound();
        }

        //check in front that file is required
        if (!file.isEmpty()) {
            fileSaver.deleteFile(targetPhoto.getAddress());
        }

        String address = fileSaver.store(file);
        targetPhoto.setRequest(requestId);
        targetPhoto.setAddress(address);
        return targetPhoto;
    }

    public void deletePhoto(long id) throws IOException {

        boolean deleted;
        Photo targetPhoto = findById(id);

        if (targetPhoto == null) {
            throw new ResourceNotFound();
        }

        final FileSaver fileSaver = new FileSaver();

        deleted = fileSaver.deleteFile(targetPhoto.getAddress());

        if (!deleted) {
            throw new FileNotFoundException();
        }

        photoRep.delete(targetPhoto);

    }

}
