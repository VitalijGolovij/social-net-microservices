package ru.goloviy.chessservice;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@EnableAdminServer
public class ChessServiceApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SpringApplication.run(ChessServiceApplication.class, args);

//		Properties properties = new Properties();
//		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//		properties.put(ProducerConfig.CLIENT_ID_CONFIG, "client1");
//		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//
//		KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
//
//		String topic = "TestTopic";
//
//		producer.send(new ProducerRecord<>(topic, "Hello world")).get();
	}

}
