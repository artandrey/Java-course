package com.example.business_models;

import com.example.business_models.interfaces.IProduct;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class ProductsPack extends ProductsContainer implements IProduct {

    @NonNull
    private final String title;

}
