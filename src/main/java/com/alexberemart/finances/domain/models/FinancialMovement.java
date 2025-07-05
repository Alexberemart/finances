package com.alexberemart.finances.domain.models;

import java.math.BigDecimal;
import java.util.Date;

public class FinancialMovement {
    private Long id;
    private Date date;
    private String description;
    private BigDecimal amount;
    private FinancialMovementCategory category;
    private BankAccount bankAccount;

    public FinancialMovement() {}

    public FinancialMovement(Long id, Date date, String description, BigDecimal amount, FinancialMovementCategory category, BankAccount bankAccount) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.bankAccount = bankAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public FinancialMovementCategory getCategory() {
        return category;
    }

    public void setCategory(FinancialMovementCategory category) {
        this.category = category;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}