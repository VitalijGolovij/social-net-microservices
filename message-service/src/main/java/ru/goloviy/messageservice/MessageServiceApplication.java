package ru.goloviy.messageservice;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
@EnableAdminServer
public class MessageServiceApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SpringApplication.run(MessageServiceApplication.class, args);
	}

}
