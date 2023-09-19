package ru.goloviy.profileservice.util.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;
import ru.goloviy.profileservice.model.User;

import java.io.IOException;
import java.util.Map;

public class UserDeserializer extends JsonDeserializer<User>{
    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Long id = node.get("id").asLong();
        String username = node.get("username").asText();
        String country = node.get("country").asText();

        User user = new User();
        user.setCountry(country);
        user.setId(id);
        user.setUsername(username);

        return user;
    }
}
