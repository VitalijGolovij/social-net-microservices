package ru.goloviy.filestorageservice.service;

import org.springframework.http.HttpEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import ru.goloviy.filestorageservice.model.User;

import java.util.List;

public interface ImageService {
    void saveImage(MultipartFile multipartFile, User user);
    byte[] getImage(String imageName);
    List<String> getImageNameList(User user);
}
