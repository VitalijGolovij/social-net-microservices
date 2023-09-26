package ru.goloviy.profileservice.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.goloviy.profileservice.model.User;
import ru.goloviy.profileservice.repository.UserRepository;

@Service
public class UserConsumer {
    private final UserRepository userRepository;

    @Autowired
    public UserConsumer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "TestTopic", groupId = "profile_service")
    public void catchUser(User user){
        userRepository.save(user);
    }
}
