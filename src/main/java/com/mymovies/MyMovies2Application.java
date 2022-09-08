package com.mymovies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableCaching
@Configuration
public class MyMovies2Application {

	public static void main(String[] args) {
		SpringApplication.run(MyMovies2Application.class, args);
	}

}
