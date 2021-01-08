package com.example.shoppingapp.product.entity;

public enum MoneyType {
    USD("Dolar", "$"),
    EUR("Euro", "€"),
    TL("Lira", "TL");

    private String label;
    private String symbol;

    MoneyType(String label, String symbol) {
        this.label = label;
        this.symbol = symbol;
    }
}
