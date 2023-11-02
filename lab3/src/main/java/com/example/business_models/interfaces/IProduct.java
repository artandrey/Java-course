package com.example.business_models.interfaces;

import com.example.business_models.IIdentifiable;

public interface IProduct extends IIdentifiable, IValuable {
    String getTitle();
}
