package ru.goloviy.emailsenderservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import ru.goloviy.emailsenderservice.dto.EmailRequest;

public class EmailDeserializer implements Deserializer<EmailRequest> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public EmailRequest deserialize(String s, byte[] bytes) {
        try {
            if (bytes == null) {
                return null;
            }
            return objectMapper.readValue(bytes, EmailRequest.class);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при десериализации данных из Kafka: " + e.getMessage(), e);
        }
    }
}
