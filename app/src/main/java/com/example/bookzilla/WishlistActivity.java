package com.example.bookzilla;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class WishlistActivity extends AppCompatActivity {

    ArrayList<Book> books = new ArrayList<>();


    public Book removeBook(Book book){
        books.remove(book);
        return book;
    }

    public void addBook(Book book){
        books.add(book);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
    }
}