package com.example.db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.db.repository.IdGenerator;

public class IdGeneratorTest {
    private IdGenerator idGenerator;

    @BeforeEach
    public void setUp() {
        idGenerator = new IdGenerator();
    }

    @Test
    public void testInitialId() {
        assertEquals(1L, idGenerator.getId());
    }

    @Test
    public void testIncrementId() {
        assertEquals(1L, idGenerator.getId());
        assertEquals(2L, idGenerator.getId());
        assertEquals(3L, idGenerator.getId());
    }
}
