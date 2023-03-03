package com.example.autocompleteelasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "book")
public class Book {

    @Id
    private String id;

    @Field(analyzer = "autocomplete", searchAnalyzer = "autocomplete")
    private String title;

    @Field(analyzer = "autocomplete", searchAnalyzer = "autocomplete")
    private String author;

    @Field(analyzer = "autocomplete", searchAnalyzer = "autocomplete")
    private String genre;

    @Field(analyzer = "autocomplete", searchAnalyzer = "autocomplete")
    private String publisher;

    @Field(analyzer = "autocomplete", searchAnalyzer = "autocomplete")
    private String language;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
