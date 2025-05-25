package com.alexberemart.finances.infraestructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alexberemart.finances.domain.useCases.CreateFinancialMovements;

@Configuration
public class AppConfig {

	@Bean
	public CreateFinancialMovements createFinancialMovements() {
		CreateFinancialMovements createFinancialMovements = new CreateFinancialMovements();
		return createFinancialMovements;
	}
}