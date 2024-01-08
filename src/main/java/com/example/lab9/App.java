package com.example.lab9;

import java.io.IOException;
import java.util.List;

import com.example.lab9.cellDefinitions.CategoryCellsDefinition;
import com.example.lab9.cellDefinitions.ProductCellsDefinition;
import com.example.lab9.cellDefinitions.UserCellsDefinition;
import com.example.lab9.dto.CategoryDTO;
import com.example.lab9.dto.ProductDTO;
import com.example.lab9.dto.UserDTO;
import com.example.lab9.excel.ExcelWriter;
import com.example.lab9.excel.SheetDefinition;
import com.example.lab9.excel.TableFileDefinition;
import com.example.lab9.service.FakeStoreApiService;

public class App {
    public static void main(String[] args) {

        FakeStoreApiService fakeStoreApiService = new FakeStoreApiService();

        List<UserDTO> users = fakeStoreApiService.getUsers();

        List<CategoryDTO> categories = fakeStoreApiService.getCategories();

        List<ProductDTO> products = fakeStoreApiService.getProducts();

        TableFileDefinition fileDefinition = new TableFileDefinition();

        fileDefinition.addDataSheet(
                SheetDefinition.<UserDTO>builder().dataShape(UserCellsDefinition.getDefenitions()).title("Users").build(), users);
        fileDefinition.addDataSheet(
                SheetDefinition.<CategoryDTO>builder().title("Categories").dataShape(CategoryCellsDefinition.getDefenitions()).build(),
                categories);
        fileDefinition.addDataSheet(
                SheetDefinition.<ProductDTO>builder().title("Products").dataShape(ProductCellsDefinition.getDefinitions()).build(),
                products);

        try {
            ExcelWriter.writeToFile(fileDefinition, "./excel.xls");
            printSuccess("\n\n\n\n\nFile excel.xls was successfully created");
        } catch (IOException e) {
            System.out.println("Failed to write file");
            System.out.println(e.getMessage());
        }

    }

    public static void printSuccess(String message) {
        String greenColor = "\u001B[32m";

        String resetColor = "\u001B[0m";

        System.out.println(greenColor + message + resetColor);

    }

}
