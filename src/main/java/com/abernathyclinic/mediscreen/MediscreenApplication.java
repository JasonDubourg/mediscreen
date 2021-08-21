package com.abernathyclinic.mediscreen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableFeignClients("com.abernathyclinic.mediscreen")
public class MediscreenApplication {
	public static void main(String[] args) {
		SpringApplication.run(MediscreenApplication.class, args);
	}

}
