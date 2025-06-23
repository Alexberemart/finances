package com.alexberemart.finances.domain.ports.repositories;

import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import java.util.List;

public interface ImportFinancialMovementRepository {
    void saveAll(List<DraftFinancialMovementDto> importFinancialMovements);
    void deleteAll();
}
