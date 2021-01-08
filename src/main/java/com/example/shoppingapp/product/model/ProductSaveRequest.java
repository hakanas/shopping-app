package com.example.shoppingapp.product.model;

import com.example.shoppingapp.product.entity.MoneyType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ProductSaveRequest {
    private String id;
    private String name;
    private String description;
    private String features;
    private BigDecimal available;
    private BigDecimal price;
    private MoneyType moneyType;
    private List<String> images;
    private String sellerId;
    private String categoryId;
}
