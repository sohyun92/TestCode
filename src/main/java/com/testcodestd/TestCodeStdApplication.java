package com.testcodestd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class TestCodeStdApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestCodeStdApplication.class, args);
    }

}
