package com.example.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class Author extends Model {
    private String name;

    public Author(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

}
