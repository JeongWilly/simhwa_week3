package com.week05.post_9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@ConfigurationPropertiesScan

@SpringBootApplication
public class Post9Application {

	public static void main(String[] args) {
		SpringApplication.run(Post9Application.class, args);
	}

}
