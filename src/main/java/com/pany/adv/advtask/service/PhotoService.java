package com.pany.adv.advtask.service;

import com.pany.adv.advtask.domain.Photo;
import com.pany.adv.advtask.domain.Request;
import com.pany.adv.advtask.exceptions.*;
import com.pany.adv.advtask.repository.PhotoRep;
import com.pany.adv.advtask.service.utils.FileSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
        String fileName;

        if (file.isEmpty() || requestId == null) {
            throw new MissingParametersException();
        }

        if (!fileSaver.isImage(file)) {
            throw new FileNotImageException();
        }

        if (fileSaver.isExists(file.getOriginalFilename())) {
            throw new DuplicateFileException();
        }

        if (photoRep.countPhotosByFileName(file.getOriginalFilename()) > 0) {
            throw new DuplicateEntityException();
        }

        fileName = fileSaver.store(file);
        Photo photo = new Photo(requestId, fileName);
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

    public byte[] getPhoto(String fileName) throws IOException {
        return fileSaver.getImage(fileName);
    }

}
