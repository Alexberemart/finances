package com.alexberemart.finances.infraestructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alexberemart.finances.application.usecases.CreateFinancialMovements;
import com.alexberemart.finances.application.usecases.SaveFinancialMovements;
import com.alexberemart.finances.application.usecases.SaveImportFinancialMovements;
import com.alexberemart.finances.domain.ports.repositories.FinancialMovementRepository;
import com.alexberemart.finances.domain.ports.repositories.ImportFinancialMovementRepository;

@Configuration
public class AppConfig {

    @Bean
    public CreateFinancialMovements createFinancialMovements() {
        return new CreateFinancialMovements();
    }

    @Bean
    public SaveImportFinancialMovements saveImportFinancialMovements(ImportFinancialMovementRepository repository) {
        return new SaveImportFinancialMovements(repository);
    }

    @Bean
    public SaveFinancialMovements saveFinancialMovements(FinancialMovementRepository repository) {
        return new SaveFinancialMovements(repository);
    }
}