package ru.goloviy.filestorageservice.service.impl;

import jakarta.persistence.Table;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import ru.goloviy.filestorageservice.exception.ImageNotFoundException;
import ru.goloviy.filestorageservice.exception.SaveImageException;
import ru.goloviy.filestorageservice.model.Image;
import ru.goloviy.filestorageservice.model.User;
import ru.goloviy.filestorageservice.repository.ImageRepository;
import ru.goloviy.filestorageservice.util.ImageCompressor;
import ru.goloviy.filestorageservice.util.ImageDecompressor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ImageServiceImplTest {
    @Mock
    private ImageRepository imageRepository;
    @Mock
    private ImageCompressor imageCompressor;
    @Mock
    private ImageDecompressor imageDecompressor;
    @InjectMocks
    private ImageServiceImpl imageService;
    private Image image;
    @BeforeEach
    public void initImage(){
        image = new Image();
        image.setId(1L);
    }
    @Test
    public void successSaveImage(){
        MultipartFile multipartFile = Mockito.mock(MultipartFile.class);
        User user = new User();
        user.setId(1L);
        user.setImages(new ArrayList<>());

        Assertions.assertDoesNotThrow(()->imageService.saveImage(multipartFile, user));
        Assertions.assertEquals(1, user.getImages().size());
    }
    @Test
    public void successGetImage(){
        String imageName = "name";
        Mockito.when(imageRepository.findByName(imageName)).thenReturn(Optional.of(image));

        Assertions.assertDoesNotThrow(()-> imageService.getImage(imageName));
    }
    @Test
    public void ImageNotFoundExceptionWhenGetImage(){
        String imageName = "name";
        Mockito.when(imageRepository.findByName(imageName)).thenReturn(Optional.empty());

        Assertions.assertThrows(ImageNotFoundException.class, ()-> imageService.getImage(imageName));
    }
}
