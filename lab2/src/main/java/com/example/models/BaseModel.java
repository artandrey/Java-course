package com.example.models;

import java.util.UUID;

import com.example.models.interfaces.IIdentifiable;
import com.example.util.IdGenerator;

import lombok.Getter;

public class BaseModel implements IIdentifiable {
    @Getter
    private UUID id;

    public BaseModel() {
        id = IdGenerator.generateUUID();
    }

}
