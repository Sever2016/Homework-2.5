package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    void givenEmptyStorageService_whenSearch_thenReturnNothing() {
        String productName = "Лук";

        Collection<SearchResult> result = searchService.searchResult(productName);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void givenStorageService_whenSearchUnknown_thenReturnFalse() {
        String productName = "Лук";
        HashSet<Searchable> searchables = new HashSet<>();
        searchables.add(new SimpleProduct("Onion", 65, UUID.randomUUID()));
        Mockito.when(storageService.getSearchableRepresentation()).thenReturn(searchables);

        Collection<SearchResult> result = searchService.searchResult(productName);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void givenStorageService_whenSearchIsOk_thenReturnGoodResults() {
        String productName = "Onion";
        HashSet<Searchable> searchables = new HashSet<>();
        SimpleProduct simpleProduct = new SimpleProduct("Onion", 65, UUID.randomUUID());
        searchables.add(simpleProduct);
        Mockito.when(storageService.getSearchableRepresentation()).thenReturn(searchables);

        Collection<SearchResult> result = searchService.searchResult(productName);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(result.iterator().next().getName(), "Onion");
        Assertions.assertEquals(result.iterator().next().getId(), simpleProduct.getId());
        Assertions.assertEquals(result.iterator().next().getContentType(), simpleProduct.getTypeOfContent());
        Assertions.assertEquals(result.size(), 1);
    }
}
