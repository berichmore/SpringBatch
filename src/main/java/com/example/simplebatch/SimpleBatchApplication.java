package com.example.simplebatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SimpleBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleBatchApplication.class, args);
    }

}
