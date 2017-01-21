package com.hucet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//@EntityScan(basePackages = "com.hucet.domain")
@SpringBootApplication
public class ShooterSecurityFormApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShooterSecurityFormApplication.class, args);
    }
}
