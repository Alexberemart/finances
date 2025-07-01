package com.alexberemart.finances.infraestructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alexberemart.finances.application.usecases.CreateDraftFinancialMovements;
import com.alexberemart.finances.application.usecases.CreateFinancialMovements;
import com.alexberemart.finances.application.usecases.SaveDraftFinancialMovements;
import com.alexberemart.finances.application.usecases.DeleteAllDraftFinancialMovements;
import com.alexberemart.finances.application.usecases.ReplaceAllDraftFinancialMovements;
import com.alexberemart.finances.application.usecases.GetAllDraftFinancialMovements;
import com.alexberemart.finances.application.usecases.GetFinancialMovements;
import com.alexberemart.finances.domain.ports.repositories.FinancialMovementRepository;
import com.alexberemart.finances.domain.ports.repositories.DraftFinancialMovementRepository;
import com.alexberemart.finances.domain.ports.repositories.BankAccountRepository;

@Configuration
public class AppConfig {

    @Bean
    public CreateDraftFinancialMovements createDraftFinancialMovements() {
        return new CreateDraftFinancialMovements();
    }

    @Bean
    public SaveDraftFinancialMovements saveDraftFinancialMovements(DraftFinancialMovementRepository repository) {
        return new SaveDraftFinancialMovements(repository);
    }

    @Bean
    public CreateFinancialMovements createFinancialMovements(
            FinancialMovementRepository repository,
            BankAccountRepository bankAccountRepository) {
        return new CreateFinancialMovements(repository, bankAccountRepository);
    }

    @Bean
    public DeleteAllDraftFinancialMovements deleteAllDraftFinancialMovements(DraftFinancialMovementRepository repository) {
        return new DeleteAllDraftFinancialMovements(repository);
    }

    @Bean
    public ReplaceAllDraftFinancialMovements replaceAllDraftFinancialMovements(
            DeleteAllDraftFinancialMovements deleteAllDraftFinancialMovements,
            SaveDraftFinancialMovements saveDraftFinancialMovements) {
        return new ReplaceAllDraftFinancialMovements(deleteAllDraftFinancialMovements, saveDraftFinancialMovements);
    }

    @Bean
    public GetAllDraftFinancialMovements getAllDraftFinancialMovements(DraftFinancialMovementRepository repository) {
        return new GetAllDraftFinancialMovements(repository);
    }

    @Bean
    public GetFinancialMovements getFinancialMovements(FinancialMovementRepository repository) {
        return new GetFinancialMovements(repository);
    }
}