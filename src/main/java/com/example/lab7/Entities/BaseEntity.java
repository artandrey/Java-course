package com.example.lab7.Entities;

import lombok.Getter;

import com.example.lab7.util.IdGenerator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BaseEntity {
    @Getter
    private int id;

    public BaseEntity() {
        id = IdGenerator.generateId();
    }
}
