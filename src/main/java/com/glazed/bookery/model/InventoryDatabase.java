package com.glazed.bookery.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class InventoryDatabase {
    private static InventoryDatabase instance;
    private List<Book> books;
    
    private InventoryDatabase() {
        books = new ArrayList<>();
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Classic", 12.99));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", "Fiction", 10.50));
        books.add(new Book("1984", "George Orwell", "Dystopian", 9.99));
    }
    
    public static synchronized InventoryDatabase getInstance() {
        if (instance == null) {
            instance = new InventoryDatabase();
        }
        return instance;
    }
    
    public void addBook(Book book) {
        books.add(book);
    }
    
    public boolean removeBook(String title) {
        return books.removeIf(b -> b.getTitle().equalsIgnoreCase(title));
    }
    
    public boolean updateBook(String title, String newTitle, String newAuthor, 
                            String newGenre, double newPrice) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.setTitle(newTitle);
                book.setAuthor(newAuthor);
                book.setGenre(newGenre);
                book.setPrice(newPrice);
                return true;
            }
        }
        return false;
    }
    
    public Book getBook(String title) {
        return books.stream()
                   .filter(b -> b.getTitle().equalsIgnoreCase(title))
                   .findFirst()
                   .orElse(null);
    }
    
    public List<Book> getAllBooks() {
        return Collections.unmodifiableList(books);
    }
    
    public Book cloneBook(String title) {
        Book book = getBook(title);
        return book != null ? book.clone() : null;
    }
    
    public Iterator<Book> getGenreIterator(String genre) {
        return new GenreFilterIterator(books, genre);
    }
}