package com.example.autocompleteelasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.SourceConfig;
import co.elastic.clients.elasticsearch.core.search.Suggester;
import com.example.autocompleteelasticsearch.model.Book;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.BaseQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookAutoCompleteService {
    private final ElasticsearchClient client;
    private final ElasticsearchOperations operations;

    public BookAutoCompleteService(ElasticsearchClient client, ElasticsearchOperations operations) {
        this.client = client;
        this.operations = operations;
    }

    public List<Book> search(String query) {
        Suggester completion = CompletionSuggestion.completion(query);
        SearchRequest searchRequest = SearchRequest.of(fn -> {
            fn.index("books");
            fn.source(SourceConfig.of(sc -> sc.filter(f -> f.includes(List.of(query)))));
            fn.suggest(completion);
            return fn;
        });

        try {
            return client.search(searchRequest, Book.class)
                    .hits()
                    .hits()
                    .stream()
                    .map(Hit::source)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            SearchRequest failRequest = SearchRequest.of(fn -> {
                fn.index("books");
                fn.size(10);
                fn.source(SourceConfig.of(sc -> sc.filter(f -> f.includes(List.of(query)))));
                return fn;
            });
            Query failQuery = new BaseQuery();
            failQuery.addFields("title");
            return operations.search(failQuery, Book.class).stream()
                    .map(SearchHit::getContent)
                    .collect(Collectors.toList());

        }

    }
}
