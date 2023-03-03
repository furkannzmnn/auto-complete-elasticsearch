package com.example.autocompleteelasticsearch;

import com.example.autocompleteelasticsearch.model.Book;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/search")
@RestController
public class SearchController {
    private final BookAutoCompleteService service;

    public SearchController(BookAutoCompleteService service) {
        this.service = service;
    }

    @RequestMapping("/autocomplete/{query}")
    public List<Book> autocomplete(@PathVariable String query) {
        return service.search(query);
    }
}
