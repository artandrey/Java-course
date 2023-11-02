package com.example.business_models.interfaces;

import java.util.UUID;

public interface IProduct extends IValuable {
    UUID getId();

    String getTitle();

}
