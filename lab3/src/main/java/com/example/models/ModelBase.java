package com.example.models;

import java.util.UUID;

import com.example.util.IdGenerator;

import lombok.Data;

@Data
public abstract class ModelBase implements IIdentifiable {

    private UUID id;

    public ModelBase() {
        this.id = IdGenerator.generateUUID();
    }

}
