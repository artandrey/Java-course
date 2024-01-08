package com.example.lab9.mapper;

import java.util.List;

import com.example.lab9.dto.ProductDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductMapper {
    private ObjectMapper objectMapper = new ObjectMapper();

    public ProductDTO mapToProductDTO(String json) {
        try {
            return objectMapper.readValue(json, ProductDTO.class);
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public List<ProductDTO> mapToProductDTOList(String jsonList) {
        try {
            TypeReference<List<ProductDTO>> typeReference = new TypeReference<List<ProductDTO>>() {
            };
            return objectMapper.readValue(jsonList, typeReference);
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
