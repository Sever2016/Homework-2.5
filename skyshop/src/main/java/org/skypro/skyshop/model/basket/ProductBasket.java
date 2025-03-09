package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> productBasket;

    public ProductBasket() {
        this.productBasket = new HashMap<>();
    }

    public void addProduct(UUID id) {
        if (productBasket.containsKey(id)) {
            productBasket.put(id, productBasket.get(id) + 1);
        } else {
            productBasket.put(id, 1);
        }
    }

    public Map<UUID, Integer> getProductBasket() {
        return Collections.unmodifiableMap(productBasket);
    }
}
