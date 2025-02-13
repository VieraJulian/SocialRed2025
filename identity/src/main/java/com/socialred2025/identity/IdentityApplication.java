package com.socialred2025.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IdentityApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentityApplication.class, args);
	}

}
