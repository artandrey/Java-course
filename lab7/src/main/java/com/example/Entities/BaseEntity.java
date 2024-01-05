package com.example.Entities;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BaseEntity {
    @Getter
    private int id;
}
