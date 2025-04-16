package com.sport.formuladata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.sport.formuladata")
@EnableJpaRepositories(basePackages = "com.sport.formuladata.infrastructure.adapter.persistence")
public class FormulaDataApplication {
    public static void main(String[] args) {
        SpringApplication.run(FormulaDataApplication.class, args);
    }

    
}
