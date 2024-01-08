package com.example.lab9.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class TableFileDefinition {
    private List<SheetDefinition<? extends Object>> sheetDefinitions = new ArrayList<>();
    private List<List<? extends Object>> dataList = new ArrayList<>();

    public <T> void addDataSheet(SheetDefinition<T> definition, List<T> data) {
        sheetDefinitions.add(definition);
        dataList.add(data);
    }

    public List<TableDefinitionPart> getDefinitionParts() {
        return IntStream.range(0, getCount()).mapToObj(i -> new TableDefinitionPart(sheetDefinitions.get(i), dataList.get(i))).toList();
    }

    @AllArgsConstructor
    @Getter
    public class TableDefinitionPart {
        private SheetDefinition<? extends Object> sheetDefinition;
        private List<? extends Object> data;
    }

    public int getCount() {
        return sheetDefinitions.size();
    }
}
