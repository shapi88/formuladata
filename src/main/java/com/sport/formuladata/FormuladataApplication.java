package com.sport.formuladata;

import com.sport.formuladata.domain.port.inbound.FetchOpenF1DataUseCase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.sport.formuladata")
@EnableJpaRepositories(basePackages = "com.sport.formuladata.infrastructure.adapter.persistence")
public class FormulaDataApplication {
    public static void main(String[] args) {
        SpringApplication.run(FormulaDataApplication.class, args);
    }

    @Bean
    @Profile("!test")
    CommandLineRunner run(FetchOpenF1DataUseCase fetchOpenF1DataUseCase) {
        return args -> {
            try {
                fetchOpenF1DataUseCase.fetchAndStoreAllData();
                System.out.println("OpenF1 data imported on startup");
            } catch (Exception e) {
                System.err.println("Failed to import OpenF1 data: " + e.getMessage());
            }
        };
    }
}