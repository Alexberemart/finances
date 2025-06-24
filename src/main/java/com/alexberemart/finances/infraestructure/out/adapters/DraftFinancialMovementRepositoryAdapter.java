package com.alexberemart.finances.infraestructure.out.adapters;

import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import com.alexberemart.finances.domain.ports.repositories.DraftFinancialMovementRepository;
import com.alexberemart.finances.infraestructure.out.entities.ImportFinancialMovementEntity;
import com.alexberemart.finances.infraestructure.out.mappers.DraftFinancialMovementMapper;
import com.alexberemart.finances.infraestructure.out.repositories.JpaImportFinancialMovementRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DraftFinancialMovementRepositoryAdapter implements DraftFinancialMovementRepository {

    private final JpaImportFinancialMovementRepository jpaRepository;

    public DraftFinancialMovementRepositoryAdapter(JpaImportFinancialMovementRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void saveAll(List<DraftFinancialMovementDto> draftFinancialMovements) {
        List<ImportFinancialMovementEntity> entities = draftFinancialMovements.stream()
            .map(DraftFinancialMovementMapper::toEntity)
            .collect(Collectors.toList());
        jpaRepository.saveAll(entities);
    }

    @Override
    public void deleteAll() {
        jpaRepository.deleteAll();
    }

    @Override
    public List<DraftFinancialMovementDto> findAll() {
        return jpaRepository.findAll().stream()
            .map(DraftFinancialMovementMapper::toDto)
            .collect(Collectors.toList());
    }
}