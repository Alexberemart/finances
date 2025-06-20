package com.alexberemart.finances.infraestructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alexberemart.finances.application.usecases.CreateFinancialMovements;

@Configuration
public class AppConfig {

    @Bean
    public CreateFinancialMovements createFinancialMovements() {
        return new CreateFinancialMovements();
    }
}