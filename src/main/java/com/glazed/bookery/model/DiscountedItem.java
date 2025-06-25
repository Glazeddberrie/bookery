package com.glazed.bookery.model;

public interface DiscountedItem {
    String getDiscountedName();
    double getOriginalPrice();
    double getDiscountedPrice(double discountPercent);
}