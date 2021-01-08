package com.example.shoppingapp.product.service;

import com.example.shoppingapp.product.entity.MoneyType;
import com.example.shoppingapp.product.repository.mongo.ProductPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductPriceService {

    private final ProductPriceRepository productPriceRepository;

    public BigDecimal getByMoneyType(String id, MoneyType usd) {
        return BigDecimal.TEN;
    }
}
