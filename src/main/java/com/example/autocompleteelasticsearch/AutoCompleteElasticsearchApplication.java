package com.example.autocompleteelasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.DelegatingJsonpMapper;
import co.elastic.clients.json.jsonb.JsonbJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.example.autocompleteelasticsearch.model.Book;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

@SpringBootApplication
public class AutoCompleteElasticsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoCompleteElasticsearchApplication.class, args);
	}

	@Bean
	public ElasticsearchClient client() {
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(
						new HttpHost("localhost", 9200, "http"),
						new HttpHost("localhost", 9201, "http")));

		ElasticsearchTransport transport = new RestClientTransport(client.getLowLevelClient(), null);
		return new ElasticsearchClient(transport);
	}






}
