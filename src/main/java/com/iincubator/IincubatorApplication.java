package com.iincubator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IincubatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(IincubatorApplication.class, args);
	}

}
