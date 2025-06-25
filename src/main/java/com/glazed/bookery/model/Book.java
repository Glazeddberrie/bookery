package com.glazed.bookery.model;


public class Book implements Cloneable {
    private String title;
    private String author;
    private String genre;
    private double price;
    
    public Book(String title, String author, String genre, double price) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
    }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    @Override
    public Book clone() {
        try {
            return (Book) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
    
    @Override
    public String toString() {
        return String.format("%s by %s (%s) - $%.2f", title, author, genre, price);
    }
}