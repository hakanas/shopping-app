package com.example.shoppingapp.product.repository.es;

import com.example.shoppingapp.product.entity.es.ProductEs;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

public interface ProductEsRepository extends ReactiveElasticsearchRepository<ProductEs, String> {
}
