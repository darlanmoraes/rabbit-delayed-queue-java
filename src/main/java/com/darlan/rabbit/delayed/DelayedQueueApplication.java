package com.darlan.rabbit.delayed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DelayedQueueApplication {

	public static void main(final String[] args) {
		SpringApplication.run(DelayedQueueApplication.class, args);
	}

}
