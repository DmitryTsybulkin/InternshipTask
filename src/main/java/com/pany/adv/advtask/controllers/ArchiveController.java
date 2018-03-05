package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.Archive;
import com.pany.adv.advtask.dto.ArchiveDTO;
import com.pany.adv.advtask.service.ArchiveService;
import com.pany.adv.advtask.service.convertors.ArchiveDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ArchiveController {

    private final ArchiveService service;

    private final ArchiveDTOConverter converter;

    @Autowired
    public ArchiveController(ArchiveService service, ArchiveDTOConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @PostMapping(value = "/archive")
    public ArchiveDTO createArchive(@RequestBody Archive archive) {
        service.createArchiveEntry(archive);
        return converter.toDto(archive);
    }

    @GetMapping(value = "/archive")
    public List<ArchiveDTO> showArchives() {
        List<Archive> archives = service.findAll();
        List<ArchiveDTO> archiveDTOS = new ArrayList<>();
        for (Archive archive : archives) {
            archiveDTOS.add(converter.toDto(archive));
        }
        return archiveDTOS;
    }

    @GetMapping(value = "/archive/{id}")
    public ArchiveDTO showArchive(@PathVariable("id") long id) {
        return converter.toDto(service.findById(id));
    }

    @PutMapping(value = "/archive/{id}")
    public ArchiveDTO updateArchive(@RequestBody Archive archive, @RequestParam long id) {
        service.updateArchive(id, archive);
        return converter.toDto(archive);
    }

    @DeleteMapping(value = "/archive/{id}")
    public void deleteArchive(@PathVariable long id) {
        service.deleteArchiveEntry(id);
    }

}
