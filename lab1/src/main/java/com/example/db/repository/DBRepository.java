package com.example.db.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.example.models.IIdetifiable;

public class DBRepository<T extends IIdetifiable> {

    private Map<Long, T> storage = new HashMap<>();
    private final IdGenerator idGenerator = new IdGenerator();

    public void add(T item) {
        item.setId(idGenerator.getId());
        storage.put(item.getId(), item);
    }

    public void delete(long id) {
        if (storage.containsKey(id)) {
            storage.remove(id);
        }
    }

    public T findById(long id) {
        return storage.get(id);
    }

    public List<T> findManyBy(Predicate<T> filter) {
        List<T> result = new ArrayList<>();
        for (T item : storage.values()) {
            if (filter.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public List<T> getAll() {
        return storage.values().stream().toList();
    }

}
