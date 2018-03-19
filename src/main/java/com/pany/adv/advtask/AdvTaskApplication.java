package com.pany.adv.advtask;

import com.pany.adv.advtask.service.*;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
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
	CommandLineRunner start(UserService userService, RequestService requestService, MunicipalityService municipalityService,
							AdvPlaceService advPlaceService, AdvConstructionService advConstructionService,
							PhotoService photoService) {
		return args -> {
			log.info("@@ Inserting Data...");
			municipalityService.insertData();
			advPlaceService.insertData();
			advConstructionService.insertData();
			userService.insertData();
			requestService.insertData();
			photoService.insertData();
			log.info("@@ Ready.");
		};
	}

	@Bean
	public KieContainer kieContainer() {
		return KieServices.Factory.get().getKieClasspathContainer();
	}
}
