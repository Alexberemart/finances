package com.alexberemart.finances.infraestructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alexberemart.finances.domain.useCases.CreateFinancialMovements;
import com.alexberemart.finances.infraestructure.out.FinancialMovementBankPort;

@Configuration
public class AppConfig {

    @Bean
    public FinancialMovementBankPort financialMovementBankPort() {
        return new FinancialMovementBankPort();
    }

    @Bean
    public CreateFinancialMovements createFinancialMovements(FinancialMovementBankPort financialMovementBankPort) {
        return new CreateFinancialMovements(financialMovementBankPort);
    }
}