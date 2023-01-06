package com.groupeisi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.groupeisi.config","com.groupeisi.mapping","com.groupeisi.service","com.groupeisi.controller",
		"com.groupeisi.entity","com.groupeisi.repository","com.groupeisi.domain","com.groupeisi.security"})
public class JWTSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(JWTSpringApplication.class, args);
	}

}

