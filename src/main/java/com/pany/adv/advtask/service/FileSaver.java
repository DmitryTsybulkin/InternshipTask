package com.pany.adv.advtask.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Service
public class FileSaver {

    @Value("${upload.path}")
    private String path;

    public String store(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        fileName = String.format("%d_%s", System.currentTimeMillis(), fileName);

        InputStream stream = file.getInputStream();
        Files.copy(stream, Paths.get(path + fileName), StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    public boolean deleteFile(String fileName) throws IOException {
        return Files.deleteIfExists(Paths.get(path + fileName));
    }

    public boolean isExists(String name) {
        return Files.exists(Paths.get(path + name), LinkOption.NOFOLLOW_LINKS);
    }

    public boolean isImage(MultipartFile file) {
        String mimeType = file.getContentType();
        return mimeType.startsWith("image");
    }

    public byte[] getImage(String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(path + fileName));
    }

}
