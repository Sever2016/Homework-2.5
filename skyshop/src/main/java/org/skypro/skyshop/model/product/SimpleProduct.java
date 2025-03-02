package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int price;

    public SimpleProduct(String productName, int price, UUID id) {
        super(productName, id);
        if (price < 1) {
            throw new IllegalArgumentException("Продукт должен что-нибудь стоить");
        }
        this.price = price;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return this.productName + ": " + this.getPrice();
    }

    @Override
    public boolean isSpecial() {
        return false;
    }
}