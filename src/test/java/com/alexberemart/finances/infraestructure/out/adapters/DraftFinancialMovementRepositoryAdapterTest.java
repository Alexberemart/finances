package com.alexberemart.finances.infraestructure.out.adapters;

import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import com.alexberemart.finances.infraestructure.out.entities.ImportFinancialMovementEntity;
import com.alexberemart.finances.infraestructure.out.repositories.JpaImportFinancialMovementRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

class DraftFinancialMovementRepositoryAdapterTest {

    @Test
    void saveAll_shouldMapDtosToEntitiesAndCallJpaRepository() {
        // Arrange
        JpaImportFinancialMovementRepository mockJpaRepo = mock(JpaImportFinancialMovementRepository.class);
        DraftFinancialMovementRepositoryAdapter adapter = new DraftFinancialMovementRepositoryAdapter(mockJpaRepo);

        DraftFinancialMovementDto dto = new DraftFinancialMovementDto();
        dto.setDate(new Date());
        dto.setDescription("desc");
        dto.setAmount(BigDecimal.TEN);
        dto.setCategoryId("cat-1"); // Use categoryId instead of label

        List<DraftFinancialMovementDto> dtos = List.of(dto);

        // Act
        adapter.saveAll(dtos);

        // Assert
        verify(mockJpaRepo, times(1)).saveAll(argThat(entities -> {
            long count = 0;
            ImportFinancialMovementEntity foundEntity = null;
            for (ImportFinancialMovementEntity e : entities) {
                foundEntity = e;
                count++;
            }
            if (count != 1) return false;
            ImportFinancialMovementEntity entity = foundEntity;
            if (entity == null) return false;
            return entity.getDate().equals(dto.getDate())
                && entity.getDescription().equals(dto.getDescription())
                && entity.getAmount().equals(dto.getAmount())
                && entity.getCategory() != null
                && entity.getCategory().getId().equals(dto.getCategoryId());
        }));
    }
}
