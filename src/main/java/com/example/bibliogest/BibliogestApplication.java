package com.example.bibliogest;

import com.example.bibliogest.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class BibliogestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BibliogestApplication.class, args);
    }
}
