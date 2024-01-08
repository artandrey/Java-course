package com.example.lab9.service;

import java.util.List;

import com.example.lab9.dto.CategoryDTO;
import com.example.lab9.dto.ProductDTO;
import com.example.lab9.dto.UserDTO;
import com.example.lab9.http.HttpClient;
import com.example.lab9.mapper.CategoryMapper;
import com.example.lab9.mapper.ProductMapper;
import com.example.lab9.mapper.UserMapper;

import lombok.SneakyThrows;

public class FakeStoreApiService {
    private final String BASE_URL = "https://fakestoreapi.com/";
    private final HttpClient httpClient = new HttpClient();
    private final UserMapper userMapper = new UserMapper();
    private final CategoryMapper categoryMapper = new CategoryMapper();
    private final ProductMapper productMapper = new ProductMapper();

    @SneakyThrows
    public List<UserDTO> getUsers() {
        String usersResponce = httpClient.getJson(BASE_URL + "users");
        return userMapper.mapToUserDTOList(usersResponce);
    }

    @SneakyThrows
    public List<CategoryDTO> getCategories() {
        String categoriesResponce = httpClient.getJson(BASE_URL + "products/categories");
        return categoryMapper.mapToCategoryDTOList(categoriesResponce);
    }

    @SneakyThrows
    public List<ProductDTO> getProducts() {
        String productsResponce = httpClient.getJson(BASE_URL + "products");
        return productMapper.mapToProductDTOList(productsResponce);
    }
}
