package com.alexberemart.finances.infraestructure.out.adapters;

import com.alexberemart.finances.domain.ports.dtos.ImportFinancialMovementDto;
import com.alexberemart.finances.domain.ports.repositories.ImportFinancialMovementRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImportFinancialMovementRepositoryAdapter implements ImportFinancialMovementRepository {

    // Inject your JPA repository or EntityManager here

    @Override
    public void saveAll(List<ImportFinancialMovementDto> importFinancialMovements) {
        // Map DTOs to entities and persist them
        // Example: jpaRepository.saveAll(mappedEntities);
    }
}
