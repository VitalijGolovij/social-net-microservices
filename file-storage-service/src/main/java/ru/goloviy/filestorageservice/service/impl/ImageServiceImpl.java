package ru.goloviy.filestorageservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import ru.goloviy.filestorageservice.exception.ImageNotFoundException;
import ru.goloviy.filestorageservice.exception.SaveImageException;
import ru.goloviy.filestorageservice.model.Image;
import ru.goloviy.filestorageservice.model.User;
import ru.goloviy.filestorageservice.repository.ImageRepository;
import ru.goloviy.filestorageservice.service.ImageService;
import ru.goloviy.filestorageservice.service.UserService;
import ru.goloviy.filestorageservice.util.ImageCompressor;
import ru.goloviy.filestorageservice.util.ImageDecompressor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ImageCompressor imageCompressor;
    private final ImageDecompressor imageDecompressor;
    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, ImageCompressor imageCompressor, ImageDecompressor imageDecompressor) {
        this.imageRepository = imageRepository;
        this.imageCompressor = imageCompressor;
        this.imageDecompressor = imageDecompressor;
    }

    @Override
    public void saveImage(MultipartFile multipartFile, User user) {
        try {
            Image image = Image.builder()
                    .name(UUID.randomUUID().toString() + ".png")
                    .type(multipartFile.getContentType())
                    .data(imageCompressor.compressImage(multipartFile.getBytes()))
                    .build();
            user.getImages().add(image);
            image.setOwner(user);
            imageRepository.save(image);
        } catch (IOException e){
            throw new SaveImageException();
        }
    }

    @Override
    public byte[] getImage(String imageName) {
        Optional<Image> imageOptional = imageRepository.findByName(imageName);
        if (imageOptional.isPresent()) {
            return imageDecompressor.decompressImage(imageOptional.get().getData());
        } else {
            throw new ImageNotFoundException(imageName);
        }
    }

    @Override
    public List<String> getImageNameList(User user) {
        return user.getImages().stream().map(Image::getName).collect(Collectors.toList());
    }
}
