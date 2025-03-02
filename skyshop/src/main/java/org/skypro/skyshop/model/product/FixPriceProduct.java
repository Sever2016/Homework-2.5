package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int PRICE = 25;

    public FixPriceProduct(String productName, UUID id) {
        super(productName, id);
    }

    @Override
    public int getPrice() {
        return PRICE;
    }

    @Override
    public String toString() {
        return this.productName + ": " + this.getPrice();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}