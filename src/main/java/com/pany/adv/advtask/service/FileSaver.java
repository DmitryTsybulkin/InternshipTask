package com.pany.adv.advtask.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSaver {

    @Value("${upload.path}")
    String path;

    public String store(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        InputStream stream = file.getInputStream();
        Files.copy(stream, Paths.get(path + fileName), StandardCopyOption.REPLACE_EXISTING);
        return path + fileName;
    }

    public boolean deleteFile(String fileAddress) throws IOException {
        return Files.deleteIfExists(Paths.get(fileAddress));
    }

    public boolean isExists(String name) {
        return Files.exists(Paths.get(path + name), LinkOption.NOFOLLOW_LINKS);
    }

    public boolean isImage(MultipartFile file) {
        String mimeType = file.getContentType();
        String type = mimeType.split("/")[0];
        return type.equals("image");
    }

    public byte[] getImage(String address) throws IOException {
        return Files.readAllBytes(Paths.get(address));
    }

}
