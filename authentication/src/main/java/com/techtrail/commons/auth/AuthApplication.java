package com.techtrail.commons.auth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
//@Configuration
@ComponentScan( basePackages =  {"com.techtrail.commons.auth"}  )
//@PropertySource(value = "classpath:application.properties")
//@PropertySource("classpath:/db-access.properties")
@PropertySource("classpath:/authentication.properties")
@EntityScan("com.techtrail.commons.auth.model")
public class AuthApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
	}
}
