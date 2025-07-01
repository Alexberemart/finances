package com.alexberemart.finances.domain.models;

import java.math.BigDecimal;
import java.util.Date;

public class FinancialMovement {
    private Long id;
    private Date date;
    private String description;
    private BigDecimal amount;
    private String label;
    private BankAccount bankAccount; // <-- Add this line

    public FinancialMovement() {}

    public FinancialMovement(Long id, Date date, String description, BigDecimal amount, String label, BankAccount bankAccount) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.label = label;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}