package com.queenmmama.safeguarding.safeguarding_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Safeguarding API Spring Boot application.
 * 
 * <p>This class contains the main method that bootstraps the application by initialising Spring Boot's auto-configuration and component scanning feature, and starting the embedded server.</p>
 * 
 */
@SpringBootApplication
public class SafeguardingApiApplication {

    /**
     * Main method that serves as the entry point for the Spring Boot application.
     * @param args command-line arguments (not used)
     * 
     */

	public static void main(String[] args) {
		SpringApplication.run(SafeguardingApiApplication.class, args);
	}

}
