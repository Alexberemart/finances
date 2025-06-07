package com.alexberemart.finances.domain.useCases;

import java.util.List;

import com.alexberemart.finances.domain.ports.dtos.FinancialMovementDto;
import com.alexberemart.finances.domain.ports.dtos.ImportMovementDto;
import com.alexberemart.finances.domain.ports.in.iFinancialMovementBankPort;

public class CreateFinancialMovements {

    private final iFinancialMovementBankPort financialMovementBankPort;

    public CreateFinancialMovements(iFinancialMovementBankPort financialMovementBankPort) {
        this.financialMovementBankPort = financialMovementBankPort;
    }

    public List<ImportMovementDto> create(List<FinancialMovementDto> financialMovements) throws Exception {
        // Delegate saving to the port and return the result (if needed)
        return financialMovementBankPort.getFinancialMovementBankPort(financialMovements);
    }
}