package com.alexberemart.finances.infraestructure.in.ports;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.alexberemart.finances.domain.ports.dtos.DraftFinancialMovementDto;
import com.alexberemart.finances.domain.ports.dtos.FinancialMovementDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class DraftFinancialMovementControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllDraftFinancialMovements_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/draft-financial-movements"))
                .andExpect(status().isOk());
    }

    @Test
    void saveDraftFinancialMovements_shouldReturnOk() throws Exception {
        DraftFinancialMovementDto dto = new DraftFinancialMovementDto();
        dto.setDate(new Date());
        dto.setDescription("desc");
        dto.setAmount(new java.math.BigDecimal("10.0"));
        dto.setLabel("label");
        dto.setBankAccountId("account-123");
        dto.setSkip(false);

        String json = objectMapper.writeValueAsString(List.of(dto));

        mockMvc.perform(post("/api/draft-financial-movements/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAllDraftFinancialMovements_shouldReturnOk() throws Exception {
        mockMvc.perform(delete("/api/draft-financial-movements"))
                .andExpect(status().isOk());
    }

    @Test
    void replaceAllDraftFinancialMovements_shouldReturnOk() throws Exception {
        DraftFinancialMovementDto dto = new DraftFinancialMovementDto();
        dto.setDate(new Date());
        dto.setDescription("desc");
        dto.setAmount(new BigDecimal("10.0"));
        dto.setLabel("label");
        dto.setBankAccountId("account-123");
        dto.setSkip(false);

        String json = objectMapper.writeValueAsString(List.of(dto));

        mockMvc.perform(post("/api/draft-financial-movements/replace-all")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void createDraftFinancialMovements_shouldReturnOk() throws Exception {
        FinancialMovementDto dto = new FinancialMovementDto();
        dto.setDate(new Date());
        dto.setDescription("desc");
        dto.setAmount(new BigDecimal("10.0"));

        String json = objectMapper.writeValueAsString(List.of(dto));

        mockMvc.perform(post("/api/draft-financial-movements/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }
}