package org.skypro.skyshop.model.basket;

import java.util.List;

public class UserBasket {
    public final List<BasketItem> basketItem;
    public final int total;

    public UserBasket(List<BasketItem> basketItem) {
        this.basketItem = basketItem;
        this.total = basketItem.stream()
                .mapToInt(i -> i.getProduct().getPrice()*i.getCount())
                .sum();
    }
}
