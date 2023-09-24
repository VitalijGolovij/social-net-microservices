package ru.goloviy.filestorageservice.exception;

public class ImageNotFoundException extends RuntimeException{
    public ImageNotFoundException(String name){
        super(String.format("image with name '%s' not found", name));
    }
}
