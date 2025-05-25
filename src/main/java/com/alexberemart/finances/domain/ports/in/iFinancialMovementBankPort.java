package com.alexberemart.finances.domain.ports.in;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.alexberemart.finances.domain.ports.dtos.FinancialMovementDto;

public interface iFinancialMovementBankPort {

    public List<FinancialMovementDto> getFinancialMovementBankPort() throws InvalidFormatException, IOException, ParseException, Exception;
}
