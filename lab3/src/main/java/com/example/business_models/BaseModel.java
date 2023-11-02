package com.example.business_models;

import java.util.UUID;

import com.example.util.IdGenerator;

import lombok.Data;

@Data
public abstract class BaseModel implements IIdentifiable {

    private UUID id;

    public BaseModel() {
        this.id = IdGenerator.generateUUID();
    }

}
