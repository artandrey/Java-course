package com.example.business_models;

import com.example.business_models.interfaces.ICart;
import com.example.business_models.interfaces.IProduct;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Cart extends ProductsContainer implements ICart {
    private static final int TITLE_LENGTH = 25;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Your cart\n");

        for (IProduct product : getProducts()) {
            String title = product.getTitle();
            if (title.length() > TITLE_LENGTH) {
                title = title.substring(0, TITLE_LENGTH);
            }
            stringBuilder
                    .append("- ").append(String.format("%-" + TITLE_LENGTH + "s", title))
                    .append(" price: ").append(product.getPrice()).append("\n");
        }

        return stringBuilder.toString();
    }
}
