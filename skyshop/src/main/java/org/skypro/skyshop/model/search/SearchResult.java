package org.skypro.skyshop.model.search;

import java.util.UUID;

public final class SearchResult {
    public final UUID id;
    public final String name;
    public final String contentType;

    public SearchResult(UUID id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;

    }

    public static SearchResult fromSearchable(Searchable searchable) {
        return new SearchResult(searchable.getId(), searchable.searchTerm(), searchable.getTypeOfContent());
    }
}
