package com.alexberemart.finances.application.usecases;

import com.alexberemart.finances.domain.models.FinancialMovement;
import com.alexberemart.finances.domain.models.FinancialMovementCategory;
import com.alexberemart.finances.domain.models.BankAccount;
import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import com.alexberemart.finances.domain.ports.repositories.FinancialMovementRepository;
import com.alexberemart.finances.domain.ports.repositories.BankAccountRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CreateFinancialMovements {

    private final FinancialMovementRepository repository;
    private final BankAccountRepository bankAccountRepository;

    public CreateFinancialMovements(FinancialMovementRepository repository, BankAccountRepository bankAccountRepository) {
        this.repository = repository;
        this.bankAccountRepository = bankAccountRepository;
    }

    public void create(List<DraftFinancialMovementDto> draftDtos) {
        List<FinancialMovement> movements = draftDtos.stream()
            .map(dto -> {
                FinancialMovement fm = new FinancialMovement();
                fm.setDate(dto.getDate());
                fm.setDescription(dto.getDescription());
                fm.setAmount(dto.getAmount());
                // Set category from categoryId
                if (dto.getCategoryId() != null) {
                    FinancialMovementCategory category = new FinancialMovementCategory();
                    category.setId(dto.getCategoryId());
                    fm.setCategory(category);
                } else {
                    fm.setCategory(null);
                }
                BankAccount account = bankAccountRepository.findById(dto.getBankAccountId())
                    .orElseThrow(() -> new IllegalArgumentException("Bank account not found: " + dto.getBankAccountId()));
                fm.setBankAccount(account);
                return fm;
            })
            .collect(Collectors.toList());
        repository.saveAll(movements);
    }
}