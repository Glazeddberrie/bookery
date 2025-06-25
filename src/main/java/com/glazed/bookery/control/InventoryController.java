package com.glazed.bookery.control;

import com.glazed.bookery.model.*;
import com.glazed.bookery.view.InventoryView;

public class InventoryController {
    private InventoryDatabase model;
    private InventoryView view;
    
    public InventoryController(InventoryDatabase model, InventoryView view) {
        this.model = model;
        this.view = view;
    }
    
    public void addBook(String title, String author, String genre, double price) {
        Book newBook = new Book(title, author, genre, price);
        model.addBook(newBook);
        view.showMessage("Added new book: " + newBook);
    }
    
    public void removeBook(String title) {
        if (model.removeBook(title)) {
            view.showMessage("Removed book: " + title);
        } else {
            view.showMessage("Book not found: " + title);
        }
    }
    
    public void updateBook(String title, String newTitle, String newAuthor, 
                         String newGenre, double newPrice) {
        if (model.updateBook(title, newTitle, newAuthor, newGenre, newPrice)) {
            view.showMessage("Updated book: " + title);
        } else {
            view.showMessage("Book not found: " + title);
        }
    }
    
    public void displayBook(String title) {
        Book book = model.getBook(title);
        if (book != null) {
            view.showMessage("Book details:\n" + book);
        } else {
            view.showMessage("Book not found: " + title);
        }
    }
    
    public void displayAllBooks() {
        view.showAllBooks(model.getAllBooks());
    }
    
    public void displayBooksByGenre(String genre) {
        view.showBooksByGenre(genre, model.getGenreIterator(genre));
    }
    
    public void cloneAndDisplayBook(String title, double newPrice) {
        Book original = model.getBook(title);
        if (original != null) {
            Book clone = model.cloneBook(title);
            if (clone != null) {
                clone.setPrice(newPrice);
                view.showClonedBook(original, clone);
            } else {
                view.showMessage("Failed to clone book: " + title);
            }
        } else {
            view.showMessage("Book not found: " + title);
        }
    }
    
    public void applyDiscountAndDisplay(String title, double discountPercent) {
        Book book = model.getBook(title);
        if (book != null) {
            DiscountedItem discountedBook = new BookDiscountAdapter(book);
            view.showDiscountedBookInfo(discountedBook, discountPercent);
        } else {
            view.showMessage("Book not found: " + title);
        }
    }
}