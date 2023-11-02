package com.example.models.Items;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class DVD extends Item {
    private float duration;

    public DVD(String title, float duration) {
        super(title);
        this.duration = duration;
    }

}
