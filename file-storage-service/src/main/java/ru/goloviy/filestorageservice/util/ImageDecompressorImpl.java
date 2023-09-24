package ru.goloviy.filestorageservice.util;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@Component
public class ImageDecompressorImpl implements ImageDecompressor{
    @Override
    public byte[] decompressImage(byte[] data){
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4096];
        try {
            while (!inflater.finished()){
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
        } catch (DataFormatException ignored) {

        }
        return outputStream.toByteArray();
    }
}
