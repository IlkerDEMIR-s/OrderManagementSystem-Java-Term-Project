package me.anant.OMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OmsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(OmsApplication.class, args);
		System.out.println("Server Started");
	}

}
