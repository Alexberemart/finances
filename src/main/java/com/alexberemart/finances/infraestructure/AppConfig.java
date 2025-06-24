package com.alexberemart.finances.infraestructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alexberemart.finances.application.usecases.CreateDraftFinancialMovements;
import com.alexberemart.finances.application.usecases.CreateFinancialMovements;
import com.alexberemart.finances.application.usecases.SaveDraftFinancialMovements;
import com.alexberemart.finances.application.usecases.DeleteAllDraftFinancialMovements;
import com.alexberemart.finances.application.usecases.ReplaceAllDraftFinancialMovements;
import com.alexberemart.finances.domain.ports.repositories.FinancialMovementRepository;
import com.alexberemart.finances.domain.ports.repositories.ImportFinancialMovementRepository;

@Configuration
public class AppConfig {

    @Bean
    public CreateDraftFinancialMovements createDraftFinancialMovements() {
        return new CreateDraftFinancialMovements();
    }

    @Bean
    public SaveDraftFinancialMovements saveDraftFinancialMovements(ImportFinancialMovementRepository repository) {
        return new SaveDraftFinancialMovements(repository);
    }

    @Bean
    public CreateFinancialMovements createFinancialMovements(FinancialMovementRepository repository) {
        return new CreateFinancialMovements(repository);
    }

    @Bean
    public DeleteAllDraftFinancialMovements deleteAllDraftFinancialMovements(ImportFinancialMovementRepository repository) {
        return new DeleteAllDraftFinancialMovements(repository);
    }

    @Bean
    public ReplaceAllDraftFinancialMovements replaceAllDraftFinancialMovements(
            DeleteAllDraftFinancialMovements deleteAllDraftFinancialMovements,
            SaveDraftFinancialMovements saveDraftFinancialMovements) {
        return new ReplaceAllDraftFinancialMovements(deleteAllDraftFinancialMovements, saveDraftFinancialMovements);
    }
}