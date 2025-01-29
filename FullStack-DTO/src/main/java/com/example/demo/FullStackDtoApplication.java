package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FullStackDtoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullStackDtoApplication.class, args);
	}

	/**
	 * Provides a ModelMapper bean for object mapping.
	 *
	 * @return a new instance of ModelMapper
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
