package com.example.lab9.cellDefinitions;

import java.util.ArrayList;
import java.util.List;

import com.example.lab9.excel.ColumnDefinition;
import com.example.lab9.excel.ColumnDefinition.ColumnDefinitionBuilder;

public class ColumnsDefinitions<T> {
    private List<ColumnDefinition<T>> definitions = new ArrayList<>();

    protected void addDefinition(ColumnDefinition<T> definition) {
        definitions.add(definition);
    }

    public List<ColumnDefinition<T>> getDefinitions() {
        return definitions;
    };

    protected ColumnDefinitionBuilder<T> builder() {
        return ColumnDefinition.<T>builder();
    }
}
