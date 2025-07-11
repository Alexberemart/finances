package com.alexberemart.finances.infraestructure.out.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "financial_movement_category")
public class FinancialMovementCategoryEntity {

    @Id
    private String id;

    private String name;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}