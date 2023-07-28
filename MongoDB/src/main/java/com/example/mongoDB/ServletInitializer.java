package com.example.mongoDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MongoDbApplication.class);
	}
//	public static void main(String[] args) {
//		SpringApplication.run(MongoDbApplication.class, args);
//	}

}
