package org.skypro.skyshop.errors;

import org.skypro.skyshop.exceptions.NoSuchProductException;

public class ShopError {
    private final String code;
    private final String message;

    public ShopError(NoSuchProductException e) {
        this.code = "404";
        this.message = e.getMessage();
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
