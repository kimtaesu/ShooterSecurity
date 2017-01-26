package com.hucet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ShooterSecurityMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShooterSecurityMailApplication.class, customizeArgs(args));
    }

    private static String[] customizeArgs(String[] args) {
        List<String> argList = new ArrayList<String>(Arrays.asList(args));
        argList.add("--spring.config.location=" +
                "classpath:mail-properties.yml");
        String[] mergeArgs = argList.toArray(new String[]{});
        return mergeArgs;
    }
}
