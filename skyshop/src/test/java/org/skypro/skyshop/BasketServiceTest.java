package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    ProductBasket productBasket;
    @Mock
    StorageService storageService;

    @InjectMocks
    BasketService basketService;

    @Test
    void givenUnknownProduct_whenPuttingProductInBasket_thenThrowNoSuchProductException() {
        Exception thrownException = new Exception();
        UUID id = UUID.randomUUID();
        Mockito.when(storageService.getProductById(id)).thenReturn(Optional.empty());

        try {
            basketService.addProduct(id);
        } catch (Exception e) {
            thrownException = e;
        }

        assertThat(thrownException)
                .isNotNull()
                .hasMessage("Такой продукт отсутствует")
                .isExactlyInstanceOf(NoSuchProductException.class);
    }

    @Test
    void givenProduct_whenPuttingProductInBasket_thenDoNotThrowNoSuchProductException() {
        Exception thrownException = new Exception();
        Product product = new SimpleProduct("Onion", 125, UUID.randomUUID());
        Mockito.when(storageService.getProductById(product.getId())).thenReturn(Optional.of(product));

        try {
            basketService.addProduct(product.getId());
        } catch (Exception e) {
            thrownException = e;
        }

        assertThat(thrownException)
                .isExactlyInstanceOf(Exception.class)
                .isNotExactlyInstanceOf(NoSuchProductException.class);
    }

    @Test
    void givenEmptyProductBasket_whenGettingUserBasket_thenReturnEmptyBasket() {
        Mockito.when(productBasket.getProductBasket()).thenReturn(new HashMap<>());

        Assertions.assertTrue(basketService.getUserBasket().basketItem.isEmpty());
    }

    @Test
    void givenProductBasket_whenGettingUserBasket_thenReturnThisBasket() {
        Product product1 = new SimpleProduct("Onion", 125, UUID.randomUUID());
        Product product2 = new SimpleProduct("Carrot", 100, UUID.randomUUID());
        Mockito.when(storageService.getProductById(product1.getId())).thenReturn(Optional.of(product1));
        Mockito.when(storageService.getProductById(product2.getId())).thenReturn(Optional.of(product2));
        Mockito.when(productBasket.getProductBasket()).thenReturn(Map.of(product1.getId(), 1, product2.getId(), 1));

        Assertions.assertEquals(basketService.getUserBasket().basketItem.size(), 2);
        Assertions.assertEquals(basketService.getUserBasket().total, 225);
        Assertions.assertEquals(basketService.getUserBasket().basketItem.get(0).getProduct(), product1);
        Assertions.assertEquals(basketService.getUserBasket().basketItem.get(1).getProduct(), product2);
    }
}
