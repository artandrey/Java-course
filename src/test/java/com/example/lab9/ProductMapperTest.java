package com.example.lab9;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.lab9.dto.ProductDTO;
import com.example.lab9.mapper.ProductMapper;

public class ProductMapperTest {
    private ProductMapper productMapper;

    @BeforeEach
    void setUp() {
        productMapper = new ProductMapper();
    }

    @Test
    void mapToProductDTO_SingleProduct() throws IOException {
        String json = """
                {
                    "id": 1,
                    "title": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                    "price": 109.95,
                    "description": "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                    "category": "men's clothing",
                    "image": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                    "rating": {
                        "rate": 3.9,
                        "count": 120
                    }
                }
                """;

        ProductDTO productDTO = productMapper.mapToProductDTO(json);

        assertEquals(1L, productDTO.getId());
        assertEquals("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops", productDTO.getTitle());
        assertEquals(109.95, productDTO.getPrice());
        assertEquals(
                "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                productDTO.getDescription());
        assertEquals("men's clothing", productDTO.getCategory());
        assertEquals("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg", productDTO.getImage());

        ProductDTO.ProductRaiting rating = productDTO.getRating();
        assertEquals(3.9, rating.getRate());
        assertEquals(120, rating.getCount());
    }

    @Test
    void mapToProductDTOList_MultipleProducts() throws IOException {
        String jsonList = """
                [
                    {
                        "id": 1,
                        "title": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                        "price": 109.95,
                        "description": "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                        "category": "men's clothing",
                        "image": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                        "rating": {
                            "rate": 3.9,
                            "count": 120
                        }
                    },
                    {
                        "id": 2,
                        "title": "Mens Casual Premium Slim Fit T-Shirts ",
                        "price": 22.3,
                        "description": "Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.",
                        "category": "men's clothing",
                        "image": "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg",
                        "rating": {
                            "rate": 4.1,
                            "count": 259
                        }
                    }
                ]
                """;

        List<ProductDTO> productDTOList = productMapper.mapToProductDTOList(jsonList);

        assertEquals(2, productDTOList.size());
    }

    @Test
    void mapToProductDTO_InvalidJson() {
        String invalidJson = "Invalid JSON";

        assertThrows(RuntimeException.class, () -> productMapper.mapToProductDTO(invalidJson));
    }

    @Test
    void mapToProductDTOList_EmptyArrayJson() throws IOException {
        String emptyArrayJson = "[]";

        List<ProductDTO> productDTOList = productMapper.mapToProductDTOList(emptyArrayJson);

        assertEquals(0, productDTOList.size());
    }
}
