package com.alexberemart.finances.infraestructure.out.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "import_financial_movements")
public class ImportFinancialMovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String description;

    private BigDecimal amount;

    private String label;

    private boolean skip;

    private String bankAccountId; // <-- Added field

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public boolean isSkip() { return skip; }
    public void setSkip(boolean skip) { this.skip = skip; }

    public String getBankAccountId() { return bankAccountId; } // <-- Getter
    public void setBankAccountId(String bankAccountId) { this.bankAccountId = bankAccountId; } // <-- Setter
}
