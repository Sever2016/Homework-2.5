package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> searchResult(String search) {
        return storageService.getSearchableRepresentation(storageService).stream()
                .filter(i -> i.searchTerm().contains(search))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toSet());
    }
}
