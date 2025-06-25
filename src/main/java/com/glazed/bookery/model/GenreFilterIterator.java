package com.glazed.bookery.model;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class GenreFilterIterator implements Iterator<Book> {
    private Iterator<Book> iterator;
    private String genreFilter;
    private Book nextBook;
    
    public GenreFilterIterator(List<Book> books, String genreFilter) {
        this.iterator = books.iterator();
        this.genreFilter = genreFilter;
        findNext();
    }
    
    private void findNext() {
        nextBook = null;
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getGenre().equalsIgnoreCase(genreFilter)) {
                nextBook = book;
                break;
            }
        }
    }
    
    @Override
    public boolean hasNext() {
        return nextBook != null;
    }
    
    @Override
    public Book next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Book result = nextBook;
        findNext();
        return result;
    }
}