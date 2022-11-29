package com.sparta.boardhomework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BoardhomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardhomeworkApplication.class, args);
    }

}
