package com.example.shoppingapp.product.entity;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "product")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
public class Product {
    private String id;
    private String name;
    private String code;
    private String description;
    private String companyId;
    private String categoryId;
    private String features;
    private List<ProductImage> productImage;
    private Boolean active;
}
