package com.example.javaxml_bookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class JavaXmlBookeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaXmlBookeeperApplication.class, args);
    }

}
