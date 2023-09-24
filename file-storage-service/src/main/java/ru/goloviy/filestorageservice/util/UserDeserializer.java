package ru.goloviy.filestorageservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import ru.goloviy.filestorageservice.model.User;

public class UserDeserializer implements Deserializer<User> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public User deserialize(String s, byte[] bytes) {
        try {
            if (bytes == null) {
                return null;
            }
            return objectMapper.readValue(bytes, User.class);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при десериализации данных из Kafka: " + e.getMessage(), e);
        }
    }
}
