package com.glazed.bookery.view;

import com.glazed.bookery.model.Book;
import com.glazed.bookery.model.DiscountedItem;
import java.util.Iterator;
import java.util.List;

public interface InventoryView {
    void showAllBooks(List<Book> books);
    void showBooksByGenre(String genre, Iterator<Book> iterator);
    void showClonedBook(Book original, Book clone);
    void showDiscountedBookInfo(DiscountedItem discountedItem, double discountPercent);
    void showMessage(String message);
}
