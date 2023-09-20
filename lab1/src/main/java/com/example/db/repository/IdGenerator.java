package com.example.db.repository;

public class IdGenerator {
    private Long id = 0L;

    public long getId() {

        return ++id;
    }
}
