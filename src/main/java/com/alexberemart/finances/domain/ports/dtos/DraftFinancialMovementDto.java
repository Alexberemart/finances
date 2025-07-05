package com.alexberemart.finances.domain.ports.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class DraftFinancialMovementDto {
    private Date date;
    private String description;
    private BigDecimal amount;
    // The label now references a FinancialMovementCategory id
    private String categoryId;
    private String bankAccountId;
    private Boolean skip; // <-- Add this field

    // Getters and setters
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }

    public String getBankAccountId() { return bankAccountId; }
    public void setBankAccountId(String bankAccountId) { this.bankAccountId = bankAccountId; }

    public Boolean getSkip() { return skip; }
    public void setSkip(Boolean skip) { this.skip = skip; }
}
