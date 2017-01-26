package com.hucet;

import com.hucet.properties.MailBindingProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShooterSecurityMailApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShooterSecurityMailApplication.class, args);
	}
}
