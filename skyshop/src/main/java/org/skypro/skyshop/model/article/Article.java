package org.skypro.skyshop.model.article;

import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {
    private String articleName;
    private String articleText;
    private final UUID id;

    public Article(String articleName, String articleText, UUID id) {
        this.articleName = articleName;
        this.articleText = articleText;
        this.id = id;
    }

    public String toString() {
        return this.articleName + "\n" + this.articleText;
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public String getTypeOfContent() {
        return "ARTICLE";
    }

    @Override
    public String searchTerm() {
        return this.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        Article article = (Article) obj;
        return Objects.equals(article.articleName, this.articleName);
    }

    @Override
    public int hashCode() {
        return this.articleName.hashCode();
    }

}
