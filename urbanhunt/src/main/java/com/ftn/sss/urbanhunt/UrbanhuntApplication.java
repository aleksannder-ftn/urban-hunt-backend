package com.ftn.sss.urbanhunt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class UrbanhuntApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrbanhuntApplication.class, args);
	}

}
