package com.example.shoppingapp.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {

    private ImageType imageType;
    private String url;

    public enum ImageType {
        FEATURE, NORMAL;
    }
}
