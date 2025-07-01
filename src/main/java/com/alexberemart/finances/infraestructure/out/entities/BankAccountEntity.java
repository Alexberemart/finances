package com.alexberemart.finances.infraestructure.out.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "bank_accounts")
public class BankAccountEntity {

    @Id
    private String id;

    private String name;

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}