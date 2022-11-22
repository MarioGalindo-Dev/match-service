package com.soccer.matches.service;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@EnableBatchProcessing
@EnableFeignClients
@EnableScheduling
public class MatchesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatchesServiceApplication.class, args);
	}

}
