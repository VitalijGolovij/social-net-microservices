package ru.goloviy.profileservice.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.apache.kafka.common.serialization.Serializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;
import ru.goloviy.profileservice.model.User;

import java.io.IOException;

public class UserSerializer implements Serializer<User> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, User user) {
        try {
            if (user == null) {
                return null;
            }
            byte[] ans = objectMapper.writeValueAsBytes(user);
            return ans;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при сериализации объекта User: " + e.getMessage(), e);
        }
    }
}