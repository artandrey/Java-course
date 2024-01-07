package com.example.exceptions;

import com.example.entities.BaseEntity;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {
    private String entityName;
    private int id;

    public EntityNotFoundException(int id, Class<? extends BaseEntity> entity) {
        super("Entity " + entity.getClass().getName() + " with id: " + id + " was not found");
        this.id = id;
        this.entityName = entity.getClass().getName();
    }

}
