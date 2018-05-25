package com.cygni.hemuppgiftdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HemuppgiftdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HemuppgiftdemoApplication.class, args);
	}
}
