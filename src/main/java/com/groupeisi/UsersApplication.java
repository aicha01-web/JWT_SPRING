package com.groupeisi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan({"com.groupeisi.controller","com.groupeisi.entity","com.groupeisi.repository","com.groupeisi.domain","com.groupeisi.service"})
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

}

