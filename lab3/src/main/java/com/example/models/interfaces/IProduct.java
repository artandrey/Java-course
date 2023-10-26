package com.example.models.interfaces;

import com.example.models.IIdentifiable;

public interface IProduct extends IIdentifiable, IValuable {
    String getTitle();
}
