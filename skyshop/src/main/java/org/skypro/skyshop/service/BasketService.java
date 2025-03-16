package org.skypro.skyshop.service;

import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(StorageService storageService, ProductBasket productBasket) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        if (storageService.getProductById(id).isEmpty()) {
            throw new NoSuchProductException();
        }
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        return new UserBasket(productBasket.getProductBasket().entrySet()
                .stream()
                .map(i -> new BasketItem(storageService.getProductById(i.getKey()).orElseThrow(), i.getValue()))
                .collect(Collectors.toCollection(ArrayList::new)));
    }
}