package com.example.bookzilla;

import java.util.ArrayList;

/**
 * this class is to look at the wishlist and the activity's added to the wishlist
 */
public class WishlistActivity {

    ArrayList<Book> books = new ArrayList<>();


    public Book removeBook(Book book){
        books.remove(book);
        return book;
    }

    public void addBook(Book book){
        books.add(book);
    }

}
