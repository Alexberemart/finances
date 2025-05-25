package com.alexberemart.finances.infraestructure.out;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.alexberemart.finances.domain.ports.dtos.FinancialMovementDto;
import com.alexberemart.finances.domain.ports.in.iFinancialMovementBankPort;

@Service
public class FinancialMovementBankPort implements iFinancialMovementBankPort {

  public List<FinancialMovementDto> getFinancialMovementBankPort()
      throws Exception {

    List<FinancialMovementDto> result = new ArrayList<>();

    File file = ResourceUtils.getFile("classpath:bbva_example.xlsx");
    Workbook workbook;
    try {
      workbook = new XSSFWorkbook(file);
    } catch (InvalidFormatException e) {
      e.printStackTrace();
      throw new Exception("no tratado");
    }

    Sheet sheet = workbook.getSheetAt(0);

    List<List<String>> data = new ArrayList<>();
    for (Row row : sheet) {
      // System.out.println(data);
      List<String> rowData = new ArrayList<>();

      for (Cell cell : row) {
        switch (cell.getCellType()) {
          case STRING:
            rowData.add(cell.getStringCellValue());
            break;
          case NUMERIC:
            double numericCellValue = cell.getNumericCellValue();
            Double doubleValue = Double.valueOf(numericCellValue);
            rowData.add(doubleValue.toString());
            break;
          case BOOLEAN:
            rowData.add(cell.getStringCellValue());
            break;
          case FORMULA:
            rowData.add(cell.getStringCellValue());
            break;
          default:
            rowData.add(" ");
        }
      }
      data.add(rowData);
    }

    // we have to remove first 5 rows
    data.subList(0, 5).clear();

    for (List<String> row : data) {
      FinancialMovementDto financialMovementDto = new FinancialMovementDto();

      String dateString = row.get(0);
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
      Date date = formatter.parse(dateString);
      financialMovementDto.setDate(date);

      // =CONCATENATE(C2;" - ";D2;" - ";I2)
      String description = String.join(" - ",
          row.get(2),
          row.get(3),
          row.get(8));
      financialMovementDto.setDescription(description);

      String amount = row.get(4);
      financialMovementDto.setAmount(Double.valueOf(amount));

      result.add(financialMovementDto);
    }

    return result;
  }
}