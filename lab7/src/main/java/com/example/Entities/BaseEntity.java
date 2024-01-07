package com.example.entities;

import lombok.Getter;

import com.example.util.IdGenerator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BaseEntity {
    @Getter
    private int id;

    public BaseEntity() {
        id = IdGenerator.generateId();
    }
}
