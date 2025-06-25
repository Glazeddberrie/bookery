package com.glazed.bookery.view;

import com.glazed.bookery.control.InventoryController;
import com.glazed.bookery.model.Book;
import com.glazed.bookery.model.DiscountedItem;
import com.glazed.bookery.model.InventoryDatabase;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class ConsoleView implements InventoryView {
    private InventoryController controller;
    private Scanner scanner;
    private InventoryDatabase model = InventoryDatabase.getInstance();

    public ConsoleView() {
        this.controller = new InventoryController(model, this);
        this.scanner = new Scanner(System.in);
    }
    
    public void start() {
        System.out.println("=== BOOKSTORE INVENTORY MANAGEMENT SYSTEM ===");
        
        while (true) {
            displayMenu();
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1": addBook(); break;
                case "2": viewAllBooks(); break;
                case "3": viewBook(); break;
                case "4": updateBook(); break;
                case "5": removeBook(); break;
                case "6": viewByGenre(); break;
                case "7": applyDiscount(); break;
                case "0": 
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void displayMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Add a new book");
        System.out.println("2. View all books");
        System.out.println("3. View book details");
        System.out.println("4. Update a book");
        System.out.println("5. Remove a book");
        System.out.println("6. View books by genre");
        System.out.println("7. Apply discount to book");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private void addBook() {
        System.out.println("\n=== ADD NEW BOOK ===");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        
        controller.addBook(title, author, genre, price);
    }
    
    private void viewAllBooks() {
        controller.displayAllBooks();
    }
    
    private void viewBook() {
        System.out.print("\nEnter book title to view: ");
        String title = scanner.nextLine();
        controller.displayBook(title);
    }
    
    private void updateBook() {
        System.out.println("\n=== UPDATE BOOK ===");
        System.out.print("Enter current title: ");
        String currentTitle = scanner.nextLine();
        
        System.out.print("New title (leave blank to keep current): ");
        String newTitle = scanner.nextLine();
        System.out.print("New author (leave blank to keep current): ");
        String newAuthor = scanner.nextLine();
        System.out.print("New genre (leave blank to keep current): ");
        String newGenre = scanner.nextLine();
        System.out.print("New price (enter 0 to keep current): ");
        double newPrice = 0;
        scanner.nextLine();
        if (scanner.nextLine().isBlank()) {
            newPrice = 0;
        } else {
            newPrice = Double.parseDouble(scanner.nextLine());
        }

        Book current = model.getBook(currentTitle);
        if (current == null) {
            showMessage("Book not found");
            return;
        }

        showMessage("Book updated successfully with new details: " + 
                        "Title: " + newTitle + ", Author: " + newAuthor + 
                        ", Genre: " + newGenre + ", Price: $" + newPrice);
        
        String finalTitle = newTitle;
        String finalAuthor = newAuthor;
        String finalGenre = newGenre;
        double finalPrice = newPrice;

        if (finalTitle.isBlank()) finalTitle = current.getTitle();
        if (finalAuthor.isBlank()) finalAuthor = current.getAuthor();
        if (finalGenre.isBlank()) finalGenre = current.getGenre();
        if (finalPrice == 0) finalPrice = current.getPrice();
        
        controller.updateBook(newTitle, finalTitle, finalAuthor, finalGenre, finalPrice);
    }
    
    private void removeBook() {
        System.out.print("\nEnter book title to remove: ");
        String title = scanner.nextLine();
        controller.removeBook(title);
    }
    
    private void viewByGenre() {
        System.out.print("\nEnter genre to filter by: ");
        String genre = scanner.nextLine();
        controller.displayBooksByGenre(genre);
    }

    private void applyDiscount() {
        System.out.print("\nEnter book title to discount: ");
        String title = scanner.nextLine();
        System.out.print("Enter discount percentage: ");
        double discount = Double.parseDouble(scanner.nextLine());
        controller.applyDiscountAndDisplay(title, discount);
    }
    
    @Override
    public void showAllBooks(List<Book> books) {
        System.out.println("\n=== ALL BOOKS ===");
        if (books.isEmpty()) {
            System.out.println("No books in inventory.");
        } else {
            books.forEach(System.out::println);
        }
    }
    
    @Override
    public void showBooksByGenre(String genre, Iterator<Book> iterator) {
        System.out.println("\n=== " + genre.toUpperCase() + " BOOKS ===");
        if (!iterator.hasNext()) {
            System.out.println("No books found in this genre.");
        }
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
    
    @Override
    public void showClonedBook(Book original, Book clone) {
        System.out.println("\n=== CLONED BOOK ===");
        System.out.println("Original: " + original);
        System.out.println("Clone: " + clone);
    }
    
    @Override
    public void showDiscountedBookInfo(DiscountedItem discountedItem, double discountPercent) {
        System.out.println("\n=== DISCOUNTED BOOK ===");
        System.out.println("Item: " + discountedItem.getDiscountedName());
        System.out.printf("Original price: $%.2f\n", discountedItem.getOriginalPrice());
        System.out.printf("Discount: %.0f%%\n", discountPercent);
        System.out.printf("Discounted price: $%.2f\n", discountedItem.getDiscountedPrice(discountPercent));
    }
    
    @Override
    public void showMessage(String message) {
        System.out.println("\n" + message);
    }
    
    public static void main(String[] args) {
        new ConsoleView().start();
    }
}