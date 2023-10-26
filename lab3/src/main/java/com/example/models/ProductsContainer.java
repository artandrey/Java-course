package com.example.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.models.interfaces.IProduct;
import com.example.models.interfaces.IProductContainable;
import com.example.models.interfaces.IValuable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class ProductsContainer extends ModelBase implements IValuable, IProductContainable {

    private final List<IProduct> products = new ArrayList<>();

    @Override
    public double getPrice() {
        return this.products.stream().mapToDouble(IValuable::getPrice).sum();
    }

    @Override
    public IProductContainable addProduct(IProduct product) {
        products.add(product);
        return this;
    }

    @Override
    public int getProductsCount() {
        return products.size();
    }

    @Override
    public void removeProduct(IProduct product) {
        products.remove(product);
    }

    @Override
    public void removeProduct(UUID id) {
        this.products.removeIf(product -> product.getId() == id);
    }

}
