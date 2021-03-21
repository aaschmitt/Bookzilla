package com.example.bookzilla;

import java.util.ArrayList;

public class Book {

    /* Instance Variables - - - - - - - - - - - - - - - - - - - - - */
    private String title;
    private String author;
    private String externalLink;
    private ArrayList<Genre> genres;
    private Status currentStatus;

    public enum Genre {
        //TODO: define all the different genres here
    }

    public enum Status {
        //TODO: define all the different statuses here
        Read,
        Reading,
        Wishlist

    }

    /* Constructor(s) - - - - - - - - - - - - - - - - - - - - - - */
    public Book() {
        genres = new ArrayList<Genre>();
    }

    public Book(String title, String author, String externalLink) {
        this.title = title;
        this.author = author;
        this.externalLink = externalLink;
        genres = new ArrayList<Genre>();
    }

    /* Getters and Setters - - - - - - - - - - - - - - - - - - - - */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public void setStatus(Status newStatus) {
        currentStatus = newStatus;
    }
}
