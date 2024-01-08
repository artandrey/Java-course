package com.example.lab9.mapper;

import java.util.List;

import com.example.lab9.dto.CategoryDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CategoryMapper {
    private ObjectMapper objectMapper = new ObjectMapper();

    private TypeReference<List<String>> typeReference = new TypeReference<List<String>>() {
    };

    public List<CategoryDTO> mapToCategoryDTOList(String json) {
        try {
            List<String> rawCategories = objectMapper.readValue(json, typeReference);
            return rawCategories.stream().map(category -> new CategoryDTO(category)).toList();
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

}
