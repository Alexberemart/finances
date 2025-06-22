package com.alexberemart.finances.infraestructure.out.adapters;

import com.alexberemart.finances.domain.ports.dtos.ImportFinancialMovementDto;
import com.alexberemart.finances.domain.ports.repositories.ImportFinancialMovementRepository;
import com.alexberemart.finances.infraestructure.out.entities.ImportFinancialMovementEntity;
import com.alexberemart.finances.infraestructure.out.repositories.JpaImportFinancialMovementRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ImportFinancialMovementRepositoryAdapter implements ImportFinancialMovementRepository {

    private final JpaImportFinancialMovementRepository jpaRepository;

    public ImportFinancialMovementRepositoryAdapter(JpaImportFinancialMovementRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void saveAll(List<ImportFinancialMovementDto> importFinancialMovements) {
        List<ImportFinancialMovementEntity> entities = importFinancialMovements.stream()
            .map(dto -> {
                ImportFinancialMovementEntity entity = new ImportFinancialMovementEntity();
                entity.setDate(dto.getDate());
                entity.setDescription(dto.getDescription());
                entity.setAmount(dto.getAmount());
                entity.setLabel(dto.getLabel());
                return entity;
            })
            .collect(Collectors.toList());
        jpaRepository.saveAll(entities);
    }
}
