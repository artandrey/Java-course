package com.example.lab9.cellDefinitions;

import java.util.List;

import com.example.lab9.dto.UserDTO;
import com.example.lab9.excel.ColumnDefinition;

public class UserCellsDefinition {
    private static ColumnsDefinitions<UserDTO> cellsDefinitions = new ColumnsDefinitions<>();

    static {
        cellsDefinitions.addDefinition(cellsDefinitions.builder().title("Id").accessor(user -> user.getId().toString()).build());
        cellsDefinitions.addDefinition(cellsDefinitions.builder().title("Email").accessor(UserDTO::getEmail).build());
        cellsDefinitions.addDefinition(cellsDefinitions.builder().title("Password").accessor(UserDTO::getPassword).build());
        cellsDefinitions.addDefinition(cellsDefinitions.builder().title("Username").accessor(UserDTO::getUsername).build());
        cellsDefinitions.addDefinition(cellsDefinitions.builder().title("Phone").accessor(UserDTO::getPhone).build());
        cellsDefinitions.addDefinition(
                cellsDefinitions.builder().title("First Name").accessor(userDTO -> userDTO.getName().getFirstName()).build());
        cellsDefinitions
                .addDefinition(cellsDefinitions.builder().title("Last Name").accessor(userDTO -> userDTO.getName().getLastName()).build());
        cellsDefinitions
                .addDefinition(cellsDefinitions.builder().title("City").accessor(userDTO -> userDTO.getAddress().getCity()).build());
        cellsDefinitions
                .addDefinition(cellsDefinitions.builder().title("Street").accessor(userDTO -> userDTO.getAddress().getStreet()).build());
        cellsDefinitions.addDefinition(
                cellsDefinitions.builder().title("Number").accessor(userDTO -> String.valueOf(userDTO.getAddress().getNumber())).build());
    }

    public static List<ColumnDefinition<UserDTO>> getDefenitions() {
        return cellsDefinitions.getDefinitions();
    }
}
