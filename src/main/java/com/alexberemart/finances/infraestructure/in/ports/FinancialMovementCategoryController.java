package com.alexberemart.finances.infraestructure.in.ports;

import com.alexberemart.finances.application.services.FinancialMovementCategoryService;
import com.alexberemart.finances.domain.models.FinancialMovementCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories") // or "/api/financial-movement-categories"
public class FinancialMovementCategoryController {

    private final FinancialMovementCategoryService service;

    public FinancialMovementCategoryController(FinancialMovementCategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<FinancialMovementCategory> getAllCategories() {
        return service.getAllCategories();
    }
}