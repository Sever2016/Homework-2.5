package org.skypro.skyshop.model.search;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public interface Searchable {

    default String getStringRepresentation() {
        return "Имя " + this.searchTerm() + " - объекта — тип " + this.getTypeOfContent();
    }

    UUID getId();

    @JsonIgnore
    String getTypeOfContent();

    @JsonIgnore
    String searchTerm();
}
