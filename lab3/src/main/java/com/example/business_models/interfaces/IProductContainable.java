package com.example.business_models.interfaces;

import java.util.UUID;

public interface IProductContainable {
    IProductContainable addProduct(IProduct product);

    void removeProduct(IProduct product);

    void removeProduct(UUID id);

    int getProductsCount();
}
