package com.svaha.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.svaha.common.entity", "com.svaha.admin.user"})
public class SvahaBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(SvahaBackEndApplication.class, args);
	}

}
