package com.alexberemart.finances.domain.ports.repositories;

import com.alexberemart.finances.domain.ports.dtos.ImportFinancialMovementDto;
import java.util.List;

public interface ImportFinancialMovementRepository {
    void saveAll(List<ImportFinancialMovementDto> importFinancialMovements);
}
