package com.alexberemart.finances.infraestructure.out.adapters;

import com.alexberemart.finances.domain.models.BankAccount;
import com.alexberemart.finances.domain.models.FinancialMovement;
import com.alexberemart.finances.domain.ports.repositories.FinancialMovementRepository;
import com.alexberemart.finances.infraestructure.out.entities.FinancialMovementEntity;
import com.alexberemart.finances.infraestructure.out.entities.BankAccountEntity;
import com.alexberemart.finances.infraestructure.out.repositories.JpaFinancialMovementRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FinancialMovementRepositoryAdapter implements FinancialMovementRepository {

    private final JpaFinancialMovementRepository jpaRepository;

    public FinancialMovementRepositoryAdapter(JpaFinancialMovementRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void saveAll(List<FinancialMovement> movements) {
        List<FinancialMovementEntity> entities = movements.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
        jpaRepository.saveAll(entities);
    }

    @Override
    public List<FinancialMovement> findByBankAccountId(String bankAccountId) {
        return jpaRepository.findByBankAccountId(bankAccountId).stream()
            .map(this::toDomain)
            .collect(Collectors.toList());
    }

    private FinancialMovementEntity toEntity(FinancialMovement movement) {
        FinancialMovementEntity entity = new FinancialMovementEntity();
        entity.setId(movement.getId());
        entity.setDate(movement.getDate());
        entity.setDescription(movement.getDescription());
        entity.setAmount(movement.getAmount());
        entity.setLabel(movement.getLabel());
        // Set bankAccountId if your entity has it
        if (movement.getBankAccount() != null) {
            BankAccountEntity bankAccountEntity = new BankAccountEntity();
            bankAccountEntity.setId(movement.getBankAccount().getId());
            entity.setBankAccount(bankAccountEntity);
        }
        return entity;
    }

    private FinancialMovement toDomain(FinancialMovementEntity entity) {
        FinancialMovement movement = new FinancialMovement();
        movement.setId(entity.getId());
        movement.setDate(entity.getDate());
        movement.setDescription(entity.getDescription());
        movement.setAmount(entity.getAmount());
        movement.setLabel(entity.getLabel());

        if (entity.getBankAccount() == null) {
            throw new IllegalStateException("FinancialMovementEntity with id " + entity.getId() + " has no related BankAccountEntity.");
        }
        movement.setBankAccount(new BankAccount(
            entity.getBankAccount().getId(),
            entity.getBankAccount().getName()
        ));

        return movement;
    }
}