package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountProduct extends Product {
    private int usualPrice, discount;

    public DiscountProduct(String productName, int usualPrice, int discount, UUID id) {
        super(productName, id);
        if (usualPrice < 1) {
            throw new IllegalArgumentException("Продкут должен что-нибудь стоить");
        }
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Неверное значение скидки товара");
        }
        this.usualPrice = usualPrice;
        this.discount = discount;
    }

    @Override
    public int getPrice() {
        return this.usualPrice - this.usualPrice * this.discount / 100;
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