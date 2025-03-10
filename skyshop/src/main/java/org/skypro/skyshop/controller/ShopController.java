package org.skypro.skyshop.controller;

import org.skypro.skyshop.errors.ShopError;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
public class ShopController {


    public StorageService storageService;
    public SearchService searchService;
    public BasketService basketService;

    public ShopController(StorageService storageService, SearchService searchService, BasketService basketService) {
        this.storageService = storageService;
        this.searchService = searchService;
        this.basketService = basketService;
    }

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

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        if (storageService.getProductById(id).isEmpty()) {
            throw new NoSuchProductException();
        }
        basketService.addProduct(id);
        return "Продукт успешно добавлен";
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }

    @ControllerAdvice
    public class ShopControllerAdvice {
        @ExceptionHandler(NoSuchProductException.class)
        public ResponseEntity<ShopError> handleNoSuchProductException(NoSuchProductException e) {
            return new ResponseEntity<>(new ShopError(e), HttpStatus.NOT_FOUND);
        }
    }
}
