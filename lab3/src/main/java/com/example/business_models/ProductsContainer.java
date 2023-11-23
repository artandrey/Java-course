package com.example.business_models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.business_models.interfaces.IProduct;
import com.example.business_models.interfaces.IProductContainable;
import com.example.business_models.interfaces.IValuable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class ProductsContainer extends BaseModel implements IValuable, IProductContainable {

    private final List<IProduct> products = new ArrayList<>();

    @Override
    public double getPrice() {
        return this.products.stream().mapToDouble(IValuable::getPrice).sum();
    }

    @Override
    public void addProduct(IProduct product) {
        products.add(product);
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
