package com.pany.adv.advtask;

import com.pany.adv.advtask.service.RequestService;
import com.pany.adv.advtask.service.MunicipalityService;
import com.pany.adv.advtask.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	CommandLineRunner start(UserService userService, RequestService requestService, MunicipalityService municipalityService) {
		return args -> {
			log.info("@@ Inserting Data...");
			municipalityService.insertData();
			requestService.insertData();
			userService.insertData();
			requestService.insertData();
			log.info("@@ findAll() call...");
			userService.findAll().forEach(user -> log.info(user.toString()));
		};
	}
}
