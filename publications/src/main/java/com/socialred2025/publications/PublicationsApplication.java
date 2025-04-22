package com.socialred2025.publications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PublicationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublicationsApplication.class, args);
	}

}
