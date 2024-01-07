package com.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Station {
    private String title;
    private double longitude;
    private double latitude;
}
