package com.example.models;

public abstract class Model implements IIdetifiable {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
