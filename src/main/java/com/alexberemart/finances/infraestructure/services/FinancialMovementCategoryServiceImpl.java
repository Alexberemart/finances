package com.alexberemart.finances.infraestructure.services;

import com.alexberemart.finances.application.services.FinancialMovementCategoryService;
import com.alexberemart.finances.domain.models.FinancialMovementCategory;
import com.alexberemart.finances.infraestructure.out.repositories.FinancialMovementCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialMovementCategoryServiceImpl implements FinancialMovementCategoryService {

    private final FinancialMovementCategoryRepository repository;

    public FinancialMovementCategoryServiceImpl(FinancialMovementCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FinancialMovementCategory> getAllCategories() {
        return repository.findAll();
    }
}