package com.example.shoppingapp.product.repository.mongo;

import com.example.shoppingapp.product.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}
