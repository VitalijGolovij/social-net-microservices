package ru.goloviy.filestorageservice.util;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;

@Component
public class ImageCompressorImpl implements ImageCompressor{
    @Override
    public byte[] compressImage(byte[] data){
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4096];
        while (!deflater.finished()){
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (IOException ignored) {

        }
        return outputStream.toByteArray();
    }
}
