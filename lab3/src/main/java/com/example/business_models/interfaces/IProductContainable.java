package com.example.business_models.interfaces;

import java.util.UUID;

public interface IProductContainable {
    void addProduct(IProduct product);

    void removeProduct(IProduct product);

    void removeProduct(UUID id);

    int getProductsCount();
}
