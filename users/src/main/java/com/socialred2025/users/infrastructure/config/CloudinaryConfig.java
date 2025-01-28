package com.socialred2025.users.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        String cloudinaryUrl = System.getenv("CLOUDINARY_URL");

        if (cloudinaryUrl == null) {
            Dotenv dotenv = Dotenv.configure()
                    .directory("./users")
                    .load();

            cloudinaryUrl = dotenv.get("CLOUDINARY_URL");

        }

        return new Cloudinary(cloudinaryUrl);
    }

}
