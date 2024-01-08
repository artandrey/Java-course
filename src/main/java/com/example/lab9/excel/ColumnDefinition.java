package com.example.lab9.excel;

import java.util.function.Function;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ColumnDefinition<T> {
    private Function<T, String> accessor;
    private String title;
}
