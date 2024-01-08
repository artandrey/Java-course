package com.example.lab9.cellDefinitions;

import java.util.List;

import com.example.lab9.dto.ProductDTO;
import com.example.lab9.excel.ColumnDefinition;

public class ProductCellsDefinition {
    private static ColumnsDefinitions<ProductDTO> cellsDefinitions = new ColumnsDefinitions<>();

    static {
        cellsDefinitions.addDefinition(cellsDefinitions.builder().title("Id").accessor(product -> product.getId().toString()).build());
        cellsDefinitions.addDefinition(cellsDefinitions.builder().title("Title").accessor(ProductDTO::getTitle).build());
        cellsDefinitions
                .addDefinition(cellsDefinitions.builder().title("Price").accessor(product -> product.getPrice().toString()).build());
        cellsDefinitions.addDefinition(cellsDefinitions.builder().title("Description").accessor(ProductDTO::getDescription).build());
        cellsDefinitions.addDefinition(cellsDefinitions.builder().title("Category").accessor(ProductDTO::getCategory).build());
        cellsDefinitions.addDefinition(cellsDefinitions.builder().title("Image").accessor(ProductDTO::getImage).build());
        cellsDefinitions.addDefinition(cellsDefinitions.builder().title("Rating")
                .accessor(productDTO -> String.valueOf(productDTO.getRating().getRate())).build());
        cellsDefinitions.addDefinition(cellsDefinitions.builder().title("Rating Count")
                .accessor(productDTO -> String.valueOf(productDTO.getRating().getCount())).build());
    }

    public static List<ColumnDefinition<ProductDTO>> getDefinitions() {
        return cellsDefinitions.getDefinitions();
    }
}