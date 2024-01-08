package com.example.lab9.cellDefinitions;

import java.util.List;

import com.example.lab9.dto.CategoryDTO;
import com.example.lab9.excel.ColumnDefinition;

public class CategoryCellsDefinition {
    private static ColumnsDefinitions<CategoryDTO> cellsDefinitions = new ColumnsDefinitions<>();

    static {
        cellsDefinitions.addDefinition(cellsDefinitions.builder().title("Category title").accessor(CategoryDTO::getName).build());
    }

    public static List<ColumnDefinition<CategoryDTO>> getDefenitions() {
        return cellsDefinitions.getDefinitions();
    }
}
