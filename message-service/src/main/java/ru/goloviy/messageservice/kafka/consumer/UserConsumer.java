package ru.goloviy.messageservice.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.goloviy.messageservice.model.User;
import ru.goloviy.messageservice.repository.UserRepository;

@Service
public class UserConsumer {
    private final UserRepository userRepository;

    @Autowired
    public UserConsumer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "TestTopic", groupId = "group_id")
    public void catchUser(User user){
        userRepository.save(user);
    }
}
