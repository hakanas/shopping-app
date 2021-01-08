package com.example.shoppingapp.product.repository.mongo;

import com.example.shoppingapp.product.entity.ProductPrice;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductPriceRepository extends ReactiveMongoRepository<ProductPrice, String> {
}
