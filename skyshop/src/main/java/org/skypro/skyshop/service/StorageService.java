package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.DiscountProduct;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> storageOfProduct;
    private final Map<UUID, Article> storageOfArticle;

    public StorageService(Map<UUID, Product> storageOfProduct, Map<UUID, Article> storageOfArticle) {
        this.storageOfProduct = storageOfProduct;
        this.storageOfArticle = storageOfArticle;
        putAllOfThis();
    }

    public Collection<Product> getStorageOfProduct() {
        return this.storageOfProduct.values();
    }

    public Collection<Article> getStorageOfArticle() {
        return this.storageOfArticle.values();
    }

    public HashSet<Searchable> getSearchableRepresentation(StorageService storageService) {
        HashSet<Searchable> result = new HashSet<>(storageService.storageOfProduct.values().stream()
                .collect(HashSet::new, HashSet<Searchable>::add, HashSet<Searchable>::addAll));
        result.addAll(storageService.storageOfArticle.values().stream()
                .collect(HashSet::new, HashSet<Searchable>::add, HashSet<Searchable>::addAll));
        return result;
    }

    public void putAllOfThis() {
        Product apple = new SimpleProduct("Apple", 45, UUID.randomUUID());
        Product grape = new DiscountProduct("Grape", 325, 15, UUID.randomUUID());
        Product buckwheat = new SimpleProduct("AAпельсино", 325, UUID.randomUUID());
        Product onion = new FixPriceProduct("Onion", UUID.randomUUID());
        Product butter = new SimpleProduct("Butter", 68, UUID.randomUUID());
        Product berries = new SimpleProduct("Berries", 517, UUID.randomUUID());
        this.storageOfProduct.put(UUID.randomUUID(), apple);
        this.storageOfProduct.put(UUID.randomUUID(), grape);
        this.storageOfProduct.put(UUID.randomUUID(), buckwheat);
        this.storageOfProduct.put(UUID.randomUUID(), onion);
        this.storageOfProduct.put(UUID.randomUUID(), butter);
        this.storageOfProduct.put(UUID.randomUUID(), berries);
        Article onionArticle = new Article("Onion", "Почему лук заставляет плакать?", UUID.randomUUID());
        Article cookiesArticle = new Article("Cookies", "Вкусно и полезно, но как?", UUID.randomUUID());
        Article amberArticle = new Article("Amber", "Сочно, дерзко, аппетитно", UUID.randomUUID());
        this.storageOfArticle.put(UUID.randomUUID(), onionArticle);
        this.storageOfArticle.put(UUID.randomUUID(), cookiesArticle);
        this.storageOfArticle.put(UUID.randomUUID(), amberArticle);
    }
}
