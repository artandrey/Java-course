package com.example.lab9.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {
    private Long id;
    private String title;
    private Number price;
    private String description;
    private String category;
    private String image;
    private ProductRaiting rating;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public class ProductRaiting {
        private double rate;
        private int count;
    }
}
