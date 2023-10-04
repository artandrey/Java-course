package com.example.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class DVD extends Item {
    private float duration;

    public DVD(String title, long id, float duration) {
        super(title, id);
        this.duration = duration;
    }

}
