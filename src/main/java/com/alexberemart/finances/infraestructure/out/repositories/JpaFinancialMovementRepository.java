package com.alexberemart.finances.infraestructure.out.repositories;

import com.alexberemart.finances.infraestructure.out.entities.FinancialMovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFinancialMovementRepository extends JpaRepository<FinancialMovementEntity, Long> {
}