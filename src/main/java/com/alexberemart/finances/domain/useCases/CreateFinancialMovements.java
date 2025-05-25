package com.alexberemart.finances.domain.useCases;

import java.util.ArrayList;
import java.util.List;

import com.alexberemart.finances.domain.ports.dtos.FinancialMovementDto;
import com.alexberemart.finances.domain.ports.in.iFinancialMovementBankPort;

public class CreateFinancialMovements {

    public List<FinancialMovementDto> create(iFinancialMovementBankPort iFinancialMovementBankPort)
            throws Exception {
        List<FinancialMovementDto> result = new ArrayList<>();
        result = iFinancialMovementBankPort.getFinancialMovementBankPort();
        return result;
    }
}