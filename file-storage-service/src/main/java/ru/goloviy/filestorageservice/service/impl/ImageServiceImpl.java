package ru.goloviy.filestorageservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.goloviy.filestorageservice.exception.ImageNotFoundException;
import ru.goloviy.filestorageservice.model.Image;
import ru.goloviy.filestorageservice.model.User;
import ru.goloviy.filestorageservice.repository.ImageRepository;
import ru.goloviy.filestorageservice.service.ImageService;
import ru.goloviy.filestorageservice.service.UserService;
import ru.goloviy.filestorageservice.util.ImageCompressor;
import ru.goloviy.filestorageservice.util.ImageDecompressor;

import java.io.IOException;
import java.util.Optional;


@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ImageCompressor imageCompressor;
    private final ImageDecompressor imageDecompressor;
    private final UserService  userService;
    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, ImageCompressor imageCompressor, ImageDecompressor imageDecompressor, UserService userService) {
        this.imageRepository = imageRepository;
        this.imageCompressor = imageCompressor;
        this.imageDecompressor = imageDecompressor;
        this.userService = userService;
    }

    @Override
    public void saveImage(MultipartFile multipartFile, Long userId) {
        try {
            User user = userService.getUserBy(userId);
            Image image = Image.builder()
                    .name(multipartFile.getOriginalFilename())
                    .type(multipartFile.getContentType())
                    .data(imageCompressor.compressImage(multipartFile.getBytes()))
                    .build();
            user.getImages().add(image);
            image.setOwner(user);
            imageRepository.save(image);
        } catch (IOException e){

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
}
