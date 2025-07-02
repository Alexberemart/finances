package com.alexberemart.finances.domain.ports.dtos;

import java.util.Date;
import java.math.BigDecimal;

public class ImportMovementDto {

    private Date date;
    private String description;
    private BigDecimal amount; // Changed from Double to BigDecimal

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}