package com.example.lab9;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.lab9.dto.CategoryDTO;
import com.example.lab9.mapper.CategoryMapper;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryMapperTest {

    private CategoryMapper categoryMapper;

    @BeforeEach
    public void setUp() {
        categoryMapper = new CategoryMapper();
    }

    @Test
    public void mapToCategoryDTOList_SingleCategory() throws IOException {
        String json = """
                ["Category1"]
                """;
        List<CategoryDTO> categoryDTOList = categoryMapper.mapToCategoryDTOList(json);

        assertEquals(1, categoryDTOList.size());
        assertEquals("Category1", categoryDTOList.get(0).getName());
    }

    @Test
    public void mapToCategoryDTOList_MultipleCategories() throws IOException {
        String json = "[\"Category1\", \"Category2\"]";
        List<CategoryDTO> categoryDTOList = categoryMapper.mapToCategoryDTOList(json);

        assertEquals(2, categoryDTOList.size());
        assertEquals("Category1", categoryDTOList.get(0).getName());
        assertEquals("Category2", categoryDTOList.get(1).getName());
    }

    @Test
    void mapToCategoryDTOList_InvalidJson() {
        String invalidJson = "Invalid JSON";
        assertThrows(RuntimeException.class, () -> categoryMapper.mapToCategoryDTOList(invalidJson));
    }

    @Test
    void mapToCategoryDTOList_EmptyArrayJson() throws IOException {
        String emptyArrayJson = "[]";
        List<CategoryDTO> categoryDTOList = categoryMapper.mapToCategoryDTOList(emptyArrayJson);

        assertEquals(0, categoryDTOList.size());
    }
}
