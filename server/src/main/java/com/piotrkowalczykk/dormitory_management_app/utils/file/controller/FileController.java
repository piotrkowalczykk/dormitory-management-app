package com.piotrkowalczykk.dormitory_management_app.utils.file.controller;

import com.piotrkowalczykk.dormitory_management_app.utils.file.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Paths;

@RestController
@RequestMapping("/api/uploads")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/{directory}/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable String directory, @PathVariable String fileName){
        Resource resource = fileService.getImage(directory + "/" + fileName);
        return resource != null ? ResponseEntity.ok(resource) : ResponseEntity.notFound().build();
    }
}
