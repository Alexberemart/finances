package com.alexberemart.finances.infraestructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alexberemart.finances.application.usecases.CreateDraftFinancialMovements;
import com.alexberemart.finances.application.usecases.CreateFinancialMovements;
import com.alexberemart.finances.application.usecases.SaveImportFinancialMovements;
import com.alexberemart.finances.domain.ports.repositories.FinancialMovementRepository;
import com.alexberemart.finances.domain.ports.repositories.ImportFinancialMovementRepository;

@Configuration
public class AppConfig {

    @Bean
    public CreateDraftFinancialMovements createDraftFinancialMovements() {
        return new CreateDraftFinancialMovements();
    }

    @Bean
    public SaveImportFinancialMovements saveImportFinancialMovements(ImportFinancialMovementRepository repository) {
        return new SaveImportFinancialMovements(repository);
    }

    @Bean
    public CreateFinancialMovements createFinancialMovements(FinancialMovementRepository repository) {
        return new CreateFinancialMovements(repository);
    }
}