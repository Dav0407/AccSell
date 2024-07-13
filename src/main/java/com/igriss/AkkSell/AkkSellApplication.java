package com.igriss.AkkSell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AkkSellApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkkSellApplication.class, args);
	}
}
