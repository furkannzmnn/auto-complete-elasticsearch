package com.example.autocompleteelasticsearch;

import co.elastic.clients.elasticsearch.core.search.FieldSuggester;
import co.elastic.clients.elasticsearch.core.search.Suggester;

import java.util.HashMap;
import java.util.Map;

public class CompletionSuggestion {
    public static Suggester completion(String field) {
        Map<String, FieldSuggester> map = new HashMap<>();

        map.put(field, FieldSuggester.of(fn ->
                fn.completion(cs ->
                        cs
                                .skipDuplicates(true)
                                .size(5)
                                .fuzzy(f -> f
                                        .fuzziness("1")
                                        .minLength(2)
                                        .prefixLength(3)
                                        .transpositions(true)
                                        .unicodeAware(true))
                                .field(field))));

        return Suggester.of(fn -> fn.suggesters(map));
    }
}
