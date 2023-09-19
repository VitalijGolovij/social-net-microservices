package ru.goloviy.chessservice.consumer;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.goloviy.chessservice.model.User;
import ru.goloviy.chessservice.repository.UserRepository;

@Service
public class ChessGameConsumer {
    private final UserRepository userRepository;

    @Autowired
    public ChessGameConsumer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "TestTopic", groupId = "group_id")
    public void createChessGame(User user){
        userRepository.save(user);
    }
}
