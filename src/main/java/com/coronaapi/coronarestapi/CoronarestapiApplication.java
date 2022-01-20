package com.coronaapi.coronarestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoronarestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronarestapiApplication.class, args);
		
	}

}
