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
        this.putAllOfThis();
    }

    public Collection<Product> getStorageOfProduct() {
        return storageOfProduct.values();
    }

    public Collection<Article> getStorageOfArticle() {
        return storageOfArticle.values();
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(storageOfProduct.get(id));
    }

    public HashSet<Searchable> getSearchableRepresentation() {
        HashSet<Searchable> result = new HashSet<>(storageOfProduct.values().stream()
                .collect(HashSet::new, HashSet<Searchable>::add, HashSet<Searchable>::addAll));
        result.addAll(storageOfArticle.values().stream()
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
        this.storageOfProduct.put(apple.getId(), apple);
        this.storageOfProduct.put(grape.getId(), grape);
        this.storageOfProduct.put(buckwheat.getId(), buckwheat);
        this.storageOfProduct.put(onion.getId(), onion);
        this.storageOfProduct.put(butter.getId(), butter);
        this.storageOfProduct.put(berries.getId(), berries);
        Article onionArticle = new Article("Onion", "Почему лук заставляет плакать?", UUID.randomUUID());
        Article cookiesArticle = new Article("Cookies", "Вкусно и полезно, но как?", UUID.randomUUID());
        Article amberArticle = new Article("Amber", "Сочно, дерзко, аппетитно", UUID.randomUUID());
        this.storageOfArticle.put(onionArticle.getId(), onionArticle);
        this.storageOfArticle.put(cookiesArticle.getId(), cookiesArticle);
        this.storageOfArticle.put(amberArticle.getId(), amberArticle);
    }
}
