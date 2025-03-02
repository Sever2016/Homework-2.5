package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ShopController {

    @Autowired
    public StorageService storageService;
    @Autowired
    public SearchService searchService;

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getStorageOfProduct();
    }
    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return storageService.getStorageOfArticle();
    }
    @GetMapping("/search")
    public Collection<SearchResult> getSearch(@RequestParam String pattern) {
        return searchService.searchResult(pattern);
    }
}
