package com.alexberemart.finances.infraestructure.out.repositories;

import com.alexberemart.finances.infraestructure.out.entities.ImportFinancialMovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaImportFinancialMovementRepository extends JpaRepository<ImportFinancialMovementEntity, Long> {
}
