package com.example.shoppingapp.product.service;

import com.example.shoppingapp.product.entity.Product;
import com.example.shoppingapp.product.entity.es.CategoryEs;
import com.example.shoppingapp.product.entity.es.CompanyEs;
import com.example.shoppingapp.product.entity.es.ProductEs;
import com.example.shoppingapp.product.repository.es.ProductEsRepository;
import io.netty.util.AsyncMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductEsService {

    private final ProductEsRepository productEsRepository;

    public Mono<ProductEs> saveNewProduct(Product product) {
        return productEsRepository.save(
                ProductEs.builder()
                        .active(product.getActive())
                        .code(product.getCode())
                        .description(product.getDescription())
                        .features(product.getFeatures())
                        .id(product.getId())
                        .name(product.getName())
                        // TODO get company name and code
                        .seller(CompanyEs.builder().id(product.getCompanyId()).name("TestCOmpanyName").build())
                        // TODO get company name and code
                        .category(CategoryEs.builder().id(product.getCategoryId()).name("TestCategoryName").build())
                        .build());

    }

    public Flux<ProductEs> findAll() {
        return productEsRepository.findAll();
    }
}
