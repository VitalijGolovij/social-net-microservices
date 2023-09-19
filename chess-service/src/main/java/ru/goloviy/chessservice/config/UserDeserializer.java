package ru.goloviy.chessservice.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import ru.goloviy.chessservice.model.User;

import java.io.IOException;

public class UserDeserializer implements Deserializer<User> {
    //TODO в бин
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public User deserialize(String s, byte[] bytes) {
        try {
            if (bytes == null) {
                return null;
            }
            // Десериализуем данные JSON в объект User
            return objectMapper.readValue(bytes, User.class);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при десериализации данных из Kafka: " + e.getMessage(), e);
        }
    }
}
