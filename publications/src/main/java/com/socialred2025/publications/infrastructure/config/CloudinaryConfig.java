package com.socialred2025.publications.infrastructure.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * The `CloudinaryConfig` class is a Java configuration class that creates a
 * Cloudinary instance based
 * on the environment variable or a value from a `.env` file.
 */
@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        String cloudinaryUrl = System.getenv("CLOUDINARY_URL");

        if (cloudinaryUrl == null) {
            Dotenv dotenv = Dotenv.configure()
                    .directory("./publications")
                    .load();

            cloudinaryUrl = dotenv.get("CLOUDINARY_URL");

        }

        return new Cloudinary(cloudinaryUrl);
    }

}
