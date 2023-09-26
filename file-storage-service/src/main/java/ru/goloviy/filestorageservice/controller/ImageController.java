package ru.goloviy.filestorageservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.goloviy.filestorageservice.model.User;
import ru.goloviy.filestorageservice.service.ImageService;
import ru.goloviy.filestorageservice.service.PrincipalService;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;
    private final PrincipalService principalService;
    @Autowired
    public ImageController(ImageService imageService, PrincipalService principalService) {
        this.imageService = imageService;
        this.principalService = principalService;
    }

    @PostMapping
    public ResponseEntity<?> saveImage(@RequestParam("image")MultipartFile file,
                                       HttpServletRequest request){
        User principalUser = principalService.getPrincipalUser(request);
        imageService.saveImage(file, principalUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{imageName}")
    public ResponseEntity<?> getImage(@PathVariable String imageName){
        byte[] image = imageService.getImage(imageName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(image);
    }
    @GetMapping
    public ResponseEntity<?> getUserImagesName(HttpServletRequest request){
        User principalUser = principalService.getPrincipalUser(request);
        List<String> imageNames = imageService.getImageNameList(principalUser);
        return new ResponseEntity<>(imageNames, HttpStatus.OK);
    }
}
