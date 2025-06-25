package com.glazed.bookery.model;

public class BookDiscountAdapter implements DiscountedItem {
    private Book book;
    
    public BookDiscountAdapter(Book book) {
        this.book = book;
    }
    
    @Override
    public String getDiscountedName() {
        return "Discounted: " + book.getTitle();
    }
    
    @Override
    public double getOriginalPrice() {
        return book.getPrice();
    }
    
    @Override
    public double getDiscountedPrice(double discountPercent) {
        return book.getPrice() * (1 - discountPercent / 100);
    }
}