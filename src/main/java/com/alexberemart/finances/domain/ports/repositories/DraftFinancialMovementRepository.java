package com.alexberemart.finances.domain.ports.repositories;

import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import java.util.List;

public interface DraftFinancialMovementRepository {
    void saveAll(List<DraftFinancialMovementDto> draftFinancialMovements);
    void deleteAll();
    List<DraftFinancialMovementDto> findAll();
}