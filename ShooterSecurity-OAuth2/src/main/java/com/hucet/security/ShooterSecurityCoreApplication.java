package com.hucet.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ShooterSecurityCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShooterSecurityCoreApplication.class, customizeArgs(args));
    }

    private static String[] customizeArgs(String[] args) {
        List<String> argList = new ArrayList<String>(Arrays.asList(args));
        argList.add("--spring.config.location=" +
                "classpath:oauth-config.yml");
        String[] mergeArgs = argList.toArray(new String[]{});
        return mergeArgs;
    }
}
