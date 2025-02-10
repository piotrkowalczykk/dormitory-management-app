package com.piotrkowalczykk.dormitory_management_app.utils.file.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public String saveFile(MultipartFile file, String directoryName);
    public Resource getImage(String fileName);
}
