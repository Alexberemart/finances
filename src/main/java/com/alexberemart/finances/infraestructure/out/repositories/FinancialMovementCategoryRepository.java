package com.alexberemart.finances.infraestructure.out.repositories;

import com.alexberemart.finances.domain.models.FinancialMovementCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialMovementCategoryRepository extends JpaRepository<FinancialMovementCategory, String> {
}