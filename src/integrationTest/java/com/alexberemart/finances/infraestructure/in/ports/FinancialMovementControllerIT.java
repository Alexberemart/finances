package com.alexberemart.finances.infraestructure.in.ports;

import com.alexberemart.finances.domain.models.BankAccount;
import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import com.alexberemart.finances.domain.ports.repositories.BankAccountRepository;
import com.alexberemart.finances.testinfra.AbstractIntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.transaction.annotation.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
class FinancialMovementControllerIT extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    void saveFinancialMovements_shouldReturnOk() throws Exception {
        // Ensure the bank account exists
        BankAccount account = new BankAccount();
        account.setId("account-123");
        account.setName("Test Account");
        bankAccountRepository.save(account);

        DraftFinancialMovementDto dto = new DraftFinancialMovementDto();
        dto.setDate(new Date());
        dto.setDescription("Test movement");
        dto.setAmount(new BigDecimal("100.00"));
        dto.setCategoryId("cat-1"); // Use categoryId instead of label
        dto.setBankAccountId("account-123");
        dto.setSkip(false);

        String json = objectMapper.writeValueAsString(List.of(dto));

        mockMvc.perform(post("/api/financial-movements")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void getFinancialMovements_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/financial-movements")
                .param("bankAccountId", "account-123"))
                .andExpect(status().isOk());
    }
}