package com.pany.adv.advtask.controllers;

import com.pany.adv.advtask.domain.Archive;
import com.pany.adv.advtask.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArchiveController {

    private final ArchiveService service;

    @Autowired
    public ArchiveController(ArchiveService service) {
        this.service = service;
    }

    @PostMapping(value = "/archive")
    public Archive createArchive(@RequestBody Archive archive) {
        service.createArchiveEntry(archive);
        return archive;
    }

    @GetMapping(value = "/archive")
    public List<Archive> showArchives() {
        return service.findAll();
    }

    @GetMapping(value = "/archive/{id}")
    public Archive showArchive(@PathVariable("id") long id) {
        return service.findById(id);
    }

    @PutMapping(value = "/archive/{id}")
    public Archive updateArchive(@RequestBody Archive archive, @RequestParam long id) {
        service.updateArchive(id, archive);
        return archive;
    }

    @DeleteMapping(value = "/archive/{id}")
    public void deleteArchive(@PathVariable long id) {
        service.deleteArchiveEntry(id);
    }

}
