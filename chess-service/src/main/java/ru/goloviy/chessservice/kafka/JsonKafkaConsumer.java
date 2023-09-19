//package ru.goloviy.chessservice.kafka;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//import ru.goloviy.chessservice.model.User;
//
//@Service
//public class JsonKafkaConsumer {
//    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);
//
//    @KafkaListener(topics = "TestTopic", groupId = "group_id")
//    public void consume(User user){
//        LOGGER.info(String.format("message get %s", user.toString()));
//        int x = 10;
//    }
//}
