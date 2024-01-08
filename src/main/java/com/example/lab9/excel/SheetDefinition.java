package com.example.lab9.excel;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SheetDefinition<T> {
    private List<ColumnDefinition<T>> dataShape;
    private String title;
}
