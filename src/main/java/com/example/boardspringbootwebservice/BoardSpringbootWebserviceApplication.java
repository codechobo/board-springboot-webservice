package com.example.boardspringbootwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



@SpringBootApplication
public class BoardSpringbootWebserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoardSpringbootWebserviceApplication.class, args);
    }
}
