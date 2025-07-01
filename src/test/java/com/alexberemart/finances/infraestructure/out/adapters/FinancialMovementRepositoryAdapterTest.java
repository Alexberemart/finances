package com.alexberemart.finances.infraestructure.out.adapters;

import com.alexberemart.finances.domain.models.BankAccount;
import com.alexberemart.finances.domain.models.FinancialMovement;
import com.alexberemart.finances.infraestructure.out.entities.BankAccountEntity;
import com.alexberemart.finances.infraestructure.out.entities.FinancialMovementEntity;
import com.alexberemart.finances.infraestructure.out.repositories.JpaFinancialMovementRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        movement.setBankAccount(new BankAccount("accId", "accName"));

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
                && entity.getLabel().equals(movement.getLabel())
                && entity.getBankAccount() != null
                && entity.getBankAccount().getId().equals("accId");
        }));
    }

    @Test
    void findByBankAccountId_shouldMapEntityToDomain() {
        // Arrange
        JpaFinancialMovementRepository mockJpaRepo = mock(JpaFinancialMovementRepository.class);
        FinancialMovementRepositoryAdapter adapter = new FinancialMovementRepositoryAdapter(mockJpaRepo);

        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankAccountEntity.setId("accId");
        bankAccountEntity.setName("accName");

        FinancialMovementEntity entity = new FinancialMovementEntity();
        entity.setId(2L);
        entity.setDate(new Date());
        entity.setDescription("desc2");
        entity.setAmount(BigDecimal.ONE);
        entity.setLabel("label2");
        entity.setBankAccount(bankAccountEntity);

        when(mockJpaRepo.findByBankAccountId("accId")).thenReturn(List.of(entity));

        // Act
        List<FinancialMovement> result = adapter.findByBankAccountId("accId");

        // Assert
        assertEquals(1, result.size());
        FinancialMovement movement = result.get(0);
        assertEquals(entity.getId(), movement.getId());
        assertEquals(entity.getDate(), movement.getDate());
        assertEquals(entity.getDescription(), movement.getDescription());
        assertEquals(entity.getAmount(), movement.getAmount());
        assertEquals(entity.getLabel(), movement.getLabel());
        assertNotNull(movement.getBankAccount());
        assertEquals("accId", movement.getBankAccount().getId());
        assertEquals("accName", movement.getBankAccount().getName());
    }

    @Test
    void toDomain_shouldThrowIfBankAccountIsNull() {
        // Arrange
        JpaFinancialMovementRepository mockJpaRepo = mock(JpaFinancialMovementRepository.class);
        FinancialMovementRepositoryAdapter adapter = new FinancialMovementRepositoryAdapter(mockJpaRepo);

        FinancialMovementEntity entity = new FinancialMovementEntity();
        entity.setId(3L);
        entity.setDate(new Date());
        entity.setDescription("desc3");
        entity.setAmount(BigDecimal.ZERO);
        entity.setLabel("label3");
        entity.setBankAccount(null);

        // Use reflection to access private method for direct test (optional, or test via public API)
        assertThrows(IllegalStateException.class, () -> {
            // Simulate call via findByBankAccountId
            when(mockJpaRepo.findByBankAccountId(anyString())).thenReturn(List.of(entity));
            adapter.findByBankAccountId("any");
        });
    }
}