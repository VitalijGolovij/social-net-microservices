package ru.goloviy.filestorageservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.goloviy.filestorageservice.exception.ImageNotFoundException;
import ru.goloviy.filestorageservice.model.Image;
import ru.goloviy.filestorageservice.repository.ImageRepository;
import ru.goloviy.filestorageservice.service.ImageService;
import ru.goloviy.filestorageservice.util.ImageCompressor;
import ru.goloviy.filestorageservice.util.ImageDecompressor;

import java.io.IOException;
import java.util.Optional;


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
    public void saveImage(MultipartFile multipartFile) {
        try {
            Image image = Image.builder()
                    .name(multipartFile.getOriginalFilename())
                    .type(multipartFile.getContentType())
                    .data(imageCompressor.compressImage(multipartFile.getBytes()))
                    .build();
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
