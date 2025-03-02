package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    protected String productName;
    private final UUID id;


    public Product(String productName, UUID id) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Неправильное название продукта");
        }
        this.productName = productName;
        this.id = id;
    }

    public String getProductName() {
        return this.productName;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public String getTypeOfContent() {
        return "PRODUCT";
    }

    @Override
    public String searchTerm() {
        return this.productName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        Product product = (Product) obj;
        return Objects.equals(product.productName, this.productName);
    }

    @Override
    public int hashCode() {
        return this.productName.hashCode();
    }
}
