package com.alexberemart.finances.domain.ports.in;

import java.util.List;

import com.alexberemart.finances.domain.ports.dtos.FinancialMovementDto;
import com.alexberemart.finances.domain.ports.dtos.ImportMovementDto;

public interface iFinancialMovementBankPort {
    List<ImportMovementDto> getFinancialMovementBankPort(List<FinancialMovementDto> financialMovements) throws Exception;
}
