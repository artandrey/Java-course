package com.example.lab9;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.lab9.dto.UserDTO;
import com.example.lab9.mapper.UserMapper;

public class UserMapperTest {
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        userMapper = new UserMapper();
    }

    @Test
    void mapToUserDTO_SingleUser() throws IOException {
        String json = """
                {
                    "id": 1,
                    "email": "john@gmail.com",
                    "username": "johnd",
                    "password": "m38rmF$",
                    "phone": "1-570-236-7033",
                    "name": {
                        "firstname": "john",
                        "lastname": "doe"
                    },
                    "address": {
                        "city": "kilcoole",
                        "street": "new road",
                        "number": 7682,
                        "zipcode": "12926-3874"
                    },
                    "__v": 0
                }
                """;

        UserDTO userDTO = userMapper.mapToUserDTO(json);

        assertEquals(1L, userDTO.getId());
        assertEquals("john@gmail.com", userDTO.getEmail());
        assertEquals("johnd", userDTO.getUsername());
        assertEquals("m38rmF$", userDTO.getPassword());
        assertEquals("1-570-236-7033", userDTO.getPhone());

        UserDTO.UserFullName name = userDTO.getName();
        assertEquals("john", name.getFirstName());
        assertEquals("doe", name.getLastName());

        UserDTO.Address address = userDTO.getAddress();
        assertEquals("kilcoole", address.getCity());
        assertEquals("new road", address.getStreet());
        assertEquals(7682, address.getNumber());
        assertEquals("12926-3874", address.getZipcode());
    }

    @Test
    void mapToUserDTOList_MultipleUsers() throws IOException {
        String jsonList = """
                [
                    {
                        "id": 1,
                        "email": "john@gmail.com",
                        "username": "johnd",
                        "password": "m38rmF$",
                        "phone": "1-570-236-7033",
                        "name": {
                            "firstname": "john",
                            "lastname": "doe"
                        },
                        "address": {
                            "city": "kilcoole",
                            "street": "new road",
                            "number": 7682,
                            "zipcode": "12926-3874"
                        },
                        "__v": 0
                    },
                    {
                        "id": 2,
                        "email": "morrison@gmail.com",
                        "username": "mor_2314",
                        "password": "83r5^_",
                        "phone": "1-570-236-7033",
                        "name": {
                            "firstname": "david",
                            "lastname": "morrison"
                        },
                        "address": {
                            "city": "kilcoole",
                            "street": "Lovers Ln",
                            "number": 7267,
                            "zipcode": "12926-3874"
                        },
                        "__v": 0
                    }
                ]
                """;

        List<UserDTO> userDTOList = userMapper.mapToUserDTOList(jsonList);

        assertEquals(2, userDTOList.size());
    }

    @Test
    void mapToUserDTO_InvalidJson() {
        String invalidJson = "Invalid JSON";

        assertThrows(RuntimeException.class, () -> userMapper.mapToUserDTO(invalidJson));
    }

    @Test
    void mapToUserDTOList_EmptyArrayJson() throws IOException {
        String emptyArrayJson = "[]";

        List<UserDTO> userDTOList = userMapper.mapToUserDTOList(emptyArrayJson);

        assertEquals(0, userDTOList.size());
    }
}
