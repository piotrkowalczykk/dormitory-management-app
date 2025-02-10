package com.piotrkowalczykk.dormitory_management_app.utils.file.service;

import com.piotrkowalczykk.dormitory_management_app.utils.file.exception.FileStorageException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    private final String BASE_UPLOAD_DIR = "/uploads";
    private final String BASE_URL = "http://localhost:8080";

    @Override
    public String saveFile(MultipartFile file, String directoryName){
        try {
            Path uploadPath = Paths.get(BASE_UPLOAD_DIR, directoryName);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String uniqueFileName = UUID.randomUUID().toString()+fileExtension;
            Path filePath = uploadPath.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return directoryName + "/" + uniqueFileName;
        } catch (IOException e){
            throw new FileStorageException("Error saving file: " + e.getMessage());
        }
    }

    @Override
    public Resource getImage(String fileName) {
        try {
            Path filePath = Paths.get(BASE_UPLOAD_DIR).resolve(fileName);
            Resource resource = new UrlResource(filePath.toUri());
            return resource.exists() && resource.isReadable() ? resource : null;
        } catch (MalformedURLException e){
            throw new RuntimeException("Invalid file path");
        }
    }

    @Override
    public boolean deleteImage(String fileName) {
        try{
            Path filePath = Paths.get(BASE_UPLOAD_DIR).resolve(fileName);
            boolean isDeleted = Files.deleteIfExists(filePath);
            return isDeleted;
        } catch (IOException e){
            throw new FileStorageException("Error deleting file: " + e.getMessage());
        }

    }
}
