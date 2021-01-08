package com.example.shoppingapp.product.service;


import com.example.shoppingapp.product.entity.MoneyType;
import com.example.shoppingapp.product.entity.Product;
import com.example.shoppingapp.product.entity.ProductImage;
import com.example.shoppingapp.product.entity.es.ProductEs;
import com.example.shoppingapp.product.model.ProductResponse;
import com.example.shoppingapp.product.model.ProductSaveRequest;
import com.example.shoppingapp.product.model.ProductSellerResponse;
import com.example.shoppingapp.product.repository.mongo.ProductRepository;
import com.example.shoppingapp.product.repository.es.ProductEsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductEsRepository productEsRepository;
    private final ProductRepository productRepository;
    private final ProductPriceService productPriceService;
    private final ProductDeliveryService productDeliveryService;
    private final ProductStockService productStockService;
    private final ProductImageService productImageService;
    private final ProductEsService productEsService;

    public Flux<ProductResponse> getAll() {
        //  ES den sorgula
        return productEsService.findAll().map(this::mapToDto);

    }


    public ProductResponse save(ProductSaveRequest request) {
        Product product = Product.builder()
                .active(Boolean.TRUE)
                .code("PR0001")
                .categoryId(request.getCategoryId())
                .companyId((request.getSellerId()))
                .description(request.getDescription())
                .features(request.getFeatures())
                .name(request.getName())
                .productImage(request.getImages()
                        .stream()
                        .map(item -> new ProductImage(ProductImage.ImageType.FEATURE, item))
                        .collect(Collectors.toList()))
                .build();

        // mongoya yaz
        product = productRepository.save(product).block();
        // ES guncelle



        // Redis guncelle
        // Es den cevap don
        // response nesnesine donstur
        return this.mapToDto(productEsService.saveNewProduct(product).block());
    }

    /**
     *  Calc fieldları isle
     *  Redis den ihtiyac alanlarını getir
     *  response nesnesine donustur
     * @return
     */

    private ProductResponse mapToDto(ProductEs item) {
        if(item == null) {
            return null;
        }

        BigDecimal productPrice = productPriceService.getByMoneyType(item.getId(), MoneyType.USD);
        return ProductResponse.builder()
                .price(productPrice)
                .name(item.getName())
                .features(item.getFeatures())
                .id(item.getId())
                .description(item.getDescription())
                .deliveryIn(productDeliveryService.getDeliveryInfo(item.getId()))
                .categoryId(item.getCategory().getId())
                .available(productStockService.getByProductId(item.getId()))
                .freeDelivery(productDeliveryService.freeDeliveryCheck(item.getId(), productPrice))
                .moneyType(MoneyType.USD)
                .image(productImageService.getProductMainImage(item.getId()))
                .seller(ProductSellerResponse.builder().id(item.getSeller().getId()).name(item.getSeller().getName()).build())
                .build();


    }

    public Mono<Long> count() {
        return productRepository.count();
    }
}
