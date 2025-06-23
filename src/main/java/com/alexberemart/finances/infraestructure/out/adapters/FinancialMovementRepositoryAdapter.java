package com.alexberemart.finances.infraestructure.out.adapters;

import com.alexberemart.finances.domain.models.FinancialMovement;
import com.alexberemart.finances.domain.ports.repositories.FinancialMovementRepository;
import com.alexberemart.finances.infraestructure.out.entities.FinancialMovementEntity;
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
        List<FinancialMovementEntity> entities = movements.stream().map(this::toEntity).collect(Collectors.toList());
        jpaRepository.saveAll(entities);
    }

    private FinancialMovementEntity toEntity(FinancialMovement movement) {
        FinancialMovementEntity entity = new FinancialMovementEntity();
        entity.setId(movement.getId());
        entity.setDate(movement.getDate());
        entity.setDescription(movement.getDescription());
        entity.setAmount(movement.getAmount());
        entity.setLabel(movement.getLabel());
        return entity;
    }
}