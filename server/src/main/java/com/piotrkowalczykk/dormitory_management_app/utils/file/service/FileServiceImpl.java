package com.piotrkowalczykk.dormitory_management_app.utils.file.service;

import com.piotrkowalczykk.dormitory_management_app.utils.file.exception.FileStorageException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    private final String BASE_UPLOAD_DIR = "/uploads";

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

            return filePath.toString();
        } catch (IOException e){
            throw new FileStorageException("Error saving file: " + e.getMessage());
        }
    }
}
