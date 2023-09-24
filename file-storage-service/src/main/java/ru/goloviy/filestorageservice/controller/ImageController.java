package ru.goloviy.filestorageservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.goloviy.filestorageservice.service.ImageService;

@RestController
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<?> saveImage(@RequestParam("image")MultipartFile file){
        imageService.saveImage(file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{imageName}")
    public ResponseEntity<?> getImage(@PathVariable String imageName){
        byte[] image = imageService.getImage(imageName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(image);
    }
}
