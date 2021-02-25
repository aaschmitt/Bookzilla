package com.example.bookzilla;

import java.util.ArrayList;

public class UserProfile {

    /* Instance Variables - - - - - - - - - - - - - - - - - - */
    private String username;
    private String name;
    private ArrayList<Book> books;

    /* Constructor(s) - - - - - - - - - - - - - - - - - - - - */
    public UserProfile(String name, String username) {
        this.name = name;
        this.username = username;
        books = new ArrayList<Book>();
    }

    /* Public methods - - - - - - - - - - - - - - - - - - - - */
    public void AddBook(Book book) {
        books.add(book);
    }

    public void RemoveBook(Book book) {
        books.remove(book);
    }

    /* Getters and Setters - - - - - - - - - - - - - - - - - */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

}
