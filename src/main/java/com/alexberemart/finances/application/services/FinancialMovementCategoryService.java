package com.alexberemart.finances.application.services;

import com.alexberemart.finances.domain.models.FinancialMovementCategory;
import java.util.List;

public interface FinancialMovementCategoryService {
    List<FinancialMovementCategory> getAllCategories();
}