package org.example.botoutlay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class BotOutlayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BotOutlayApplication.class, args);
	}

}
