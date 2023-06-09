package com.teacherfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@OpenAPIDefinition
@SpringBootApplication
@EnableJpaAuditing
public class TeacherFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeacherFinderApplication.class, args);
	}

}
