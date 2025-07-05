package com.alexberemart.finances.application.usecases;

import com.alexberemart.finances.domain.models.FinancialMovement;
import com.alexberemart.finances.domain.models.BankAccount;
import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import com.alexberemart.finances.domain.ports.repositories.FinancialMovementRepository;
import com.alexberemart.finances.domain.ports.repositories.BankAccountRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class CreateFinancialMovementsTest {

    @Test
    void create_shouldMapDtosToEntitiesAndCallRepository() {
        // Arrange
        FinancialMovementRepository mockRepo = mock(FinancialMovementRepository.class);
        BankAccountRepository mockBankAccountRepo = mock(BankAccountRepository.class);

        String bankAccountId = "account-123";
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(bankAccountId);
        bankAccount.setName("Test Account");

        when(mockBankAccountRepo.findById(bankAccountId)).thenReturn(Optional.of(bankAccount));

        CreateFinancialMovements useCase = new CreateFinancialMovements(mockRepo, mockBankAccountRepo);

        DraftFinancialMovementDto dto = new DraftFinancialMovementDto();
        dto.setDate(new Date());
        dto.setDescription("desc");
        dto.setAmount(BigDecimal.TEN);
        dto.setCategoryId("cat-1"); // Use categoryId instead of label
        dto.setBankAccountId(bankAccountId);

        List<DraftFinancialMovementDto> dtos = List.of(dto);

        // Act
        useCase.create(dtos);

        // Assert
        verify(mockRepo, times(1)).saveAll(argThat(entities -> {
            if (entities.size() != 1) return false;
            FinancialMovement entity = entities.get(0);
            return entity.getDate().equals(dto.getDate())
                && entity.getDescription().equals(dto.getDescription())
                && entity.getAmount().equals(dto.getAmount())
                && entity.getCategory() != null
                && entity.getCategory().getId().equals(dto.getCategoryId());
        }));
    }
}
