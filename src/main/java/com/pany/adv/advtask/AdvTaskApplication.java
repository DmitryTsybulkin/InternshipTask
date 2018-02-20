package com.pany.adv.advtask;

import com.pany.adv.advtask.service.ConstructionRequestService;
import com.pany.adv.advtask.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdvTaskApplication {

	private static final Logger log = LoggerFactory.getLogger(AdvTaskApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AdvTaskApplication.class, args);
	}

	@Bean
	CommandLineRunner start(UserService service, ConstructionRequestService requestService) {
		return args -> {
			log.info("@@ Inserting Data...");
			requestService.insertData();
			service.insertData();
			log.info("@@ findAll() call...");
			service.findAll().forEach(user -> log.info(user.toString()));
		};
	}
}
