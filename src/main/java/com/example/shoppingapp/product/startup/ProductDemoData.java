package com.example.shoppingapp.product.startup;

import com.example.shoppingapp.product.entity.MoneyType;
import com.example.shoppingapp.product.model.ProductSaveRequest;
import com.example.shoppingapp.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class ProductDemoData {

    private final ProductService productService;


    @EventListener(ApplicationReadyEvent.class)
    public void migrate() {
        Long size = productService.count().block();
        System.out.println("HAKANN: " + size);

        if (size.equals(0L)) {
            IntStream.range(0, 20).forEach(i -> {
                productService.save(
                        ProductSaveRequest.builder()
                                .sellerId(UUID.randomUUID().toString())
                                .id(UUID.randomUUID().toString())
                                .description("Product desc: " + i)
                                .moneyType(MoneyType.USD)
                                .categoryId(UUID.randomUUID().toString())
                                .name("Product name: " + i)
                                .features("<li>Black Color</li> <li>Aluminum Case</li> <li>2 Years Warranty</li> <li>5 Inch (35x55mm)</li>")
                                .price(BigDecimal.valueOf(25))
                                .images(List.of("https://productimages.hepsiburada.net/s/32/500/10352568139826.jpg"))
                                .build());
            });
        }
        System.out.println("ASLAN: " + productService.count().block());
    }
}
