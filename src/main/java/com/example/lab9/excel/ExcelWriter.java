package com.example.lab9.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelWriter {

    public static void writeToFile(TableFileDefinition fileDefinition, String filePath) throws IOException {
        try (Workbook workbook = new HSSFWorkbook()) {
            fileDefinition.getDefinitionParts().stream().forEach(definitionPart -> {
                SheetDefinition<? extends Object> sheetDefinition = definitionPart.getSheetDefinition();
                List<? extends Object> data = definitionPart.getData();
                Sheet sheet = workbook.createSheet(sheetDefinition.getTitle());
                List<String> headers = sheetDefinition.getDataShape().stream().map(cell -> cell.getTitle()).toList();
                Row headerRow = sheet.createRow(0);
                IntStream.range(0, headers.size()).forEach(cellIndex -> {
                    headerRow.createCell(cellIndex).setCellValue(headers.get(cellIndex));
                });
                IntStream.range(0, data.size()).forEach(rowIndex -> {
                    var dataPart = data.get(rowIndex);
                    Row row = sheet.createRow(rowIndex + 1);
                    IntStream.range(0, sheetDefinition.getDataShape().size()).forEach(cellIndex -> {
                        row.createCell(cellIndex).setCellValue(
                                ((Function<Object, String>) sheetDefinition.getDataShape().get(cellIndex).getAccessor()).apply(dataPart));
                    });
                });
            });
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        }

    }

}