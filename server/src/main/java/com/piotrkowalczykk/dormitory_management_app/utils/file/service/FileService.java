package com.piotrkowalczykk.dormitory_management_app.utils.file.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public String saveFile(MultipartFile file, String directoryName);
}
