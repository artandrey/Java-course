package com.example.lab9.mapper;

import java.util.List;

import com.example.lab9.dto.UserDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserMapper {
    private ObjectMapper objectMapper = new ObjectMapper();

    public UserDTO mapToUserDTO(String json) {
        try {
            return objectMapper.readValue(json, UserDTO.class);
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public List<UserDTO> mapToUserDTOList(String jsonList) {
        try {
            TypeReference<List<UserDTO>> typeReference = new TypeReference<List<UserDTO>>() {
            };
            return objectMapper.readValue(jsonList, typeReference);
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
