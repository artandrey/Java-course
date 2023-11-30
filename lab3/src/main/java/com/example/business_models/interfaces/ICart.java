package com.example.business_models.interfaces;

public interface ICart extends IValuable {
    void addProduct(IProduct product);

    void removeProduct(IProduct product);
}
