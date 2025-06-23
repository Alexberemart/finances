package com.alexberemart.finances.infraestructure.out.adapters;

import com.alexberemart.finances.domain.models.FinancialMovement;
import com.alexberemart.finances.infraestructure.out.entities.FinancialMovementEntity;
import com.alexberemart.finances.infraestructure.out.repositories.JpaFinancialMovementRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

class FinancialMovementRepositoryAdapterTest {

    @Test
    void saveAll_shouldMapDomainToEntityAndCallJpaRepository() {
        // Arrange
        JpaFinancialMovementRepository mockJpaRepo = mock(JpaFinancialMovementRepository.class);
        FinancialMovementRepositoryAdapter adapter = new FinancialMovementRepositoryAdapter(mockJpaRepo);

        FinancialMovement movement = new FinancialMovement();
        movement.setId(1L);
        movement.setDate(new Date());
        movement.setDescription("desc");
        movement.setAmount(BigDecimal.TEN);
        movement.setLabel("label");

        List<FinancialMovement> movements = List.of(movement);

        // Act
        adapter.saveAll(movements);

        // Assert
        verify(mockJpaRepo, times(1)).saveAll(argThat(entities -> {
            // Convert Iterable to List for size() and get()
            List<FinancialMovementEntity> entityList = new java.util.ArrayList<>();
            entities.forEach(entityList::add);
            if (entityList.size() != 1) return false;
            FinancialMovementEntity entity = entityList.get(0);
            return entity.getId().equals(movement.getId())
                && entity.getDate().equals(movement.getDate())
                && entity.getDescription().equals(movement.getDescription())
                && entity.getAmount().equals(movement.getAmount())
                && entity.getLabel().equals(movement.getLabel());
        }));
    }
}