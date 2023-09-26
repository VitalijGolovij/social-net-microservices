package ru.goloviy.filestorageservice.exception;

public class SaveImageException extends RuntimeException{
    public SaveImageException(){
        super("cannot save image");
    }
}
