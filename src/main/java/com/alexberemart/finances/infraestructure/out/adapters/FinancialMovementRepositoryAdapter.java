package com.alexberemart.finances.infraestructure.out.adapters;

import com.alexberemart.finances.domain.models.BankAccount;
import com.alexberemart.finances.domain.models.FinancialMovement;
import com.alexberemart.finances.domain.models.FinancialMovementCategory;
import com.alexberemart.finances.domain.ports.repositories.FinancialMovementRepository;
import com.alexberemart.finances.infraestructure.out.entities.FinancialMovementEntity;
import com.alexberemart.finances.infraestructure.out.entities.BankAccountEntity;
import com.alexberemart.finances.infraestructure.out.entities.FinancialMovementCategoryEntity;
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

        // Map category
        if (movement.getCategory() != null) {
            FinancialMovementCategoryEntity categoryEntity = new FinancialMovementCategoryEntity();
            categoryEntity.setId(movement.getCategory().getId());
            categoryEntity.setName(movement.getCategory().getName());
            entity.setCategory(categoryEntity);
        } else {
            entity.setCategory(null);
        }

        // Map bank account
        if (movement.getBankAccount() != null) {
            BankAccountEntity bankAccountEntity = new BankAccountEntity();
            bankAccountEntity.setId(movement.getBankAccount().getId());
            bankAccountEntity.setName(movement.getBankAccount().getName());
            entity.setBankAccount(bankAccountEntity);
        } else {
            entity.setBankAccount(null);
        }
        return entity;
    }

    private FinancialMovement toDomain(FinancialMovementEntity entity) {
        FinancialMovement movement = new FinancialMovement();
        movement.setId(entity.getId());
        movement.setDate(entity.getDate());
        movement.setDescription(entity.getDescription());
        movement.setAmount(entity.getAmount());

        // Map category
        if (entity.getCategory() != null) {
            FinancialMovementCategory category = new FinancialMovementCategory();
            category.setId(entity.getCategory().getId());
            category.setName(entity.getCategory().getName());
            movement.setCategory(category);
        } else {
            movement.setCategory(null);
        }

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