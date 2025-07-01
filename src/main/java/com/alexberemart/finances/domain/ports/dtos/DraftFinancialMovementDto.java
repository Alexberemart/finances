package com.alexberemart.finances.domain.ports.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class DraftFinancialMovementDto {
    private Date date;
    private String description;
    private BigDecimal amount;
    private String label;
    private String bankAccountId;
    private Boolean skip; // <-- Add this field

    // Getters and setters
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public String getBankAccountId() { return bankAccountId; }
    public void setBankAccountId(String bankAccountId) { this.bankAccountId = bankAccountId; }

    public Boolean getSkip() { return skip; }
    public void setSkip(Boolean skip) { this.skip = skip; }
}
