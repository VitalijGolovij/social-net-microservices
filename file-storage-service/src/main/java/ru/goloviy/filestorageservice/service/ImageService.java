package ru.goloviy.filestorageservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImage(MultipartFile multipartFile);
    byte[] getImage(String imageName);
}
