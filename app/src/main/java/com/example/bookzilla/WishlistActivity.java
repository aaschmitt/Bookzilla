package com.example.bookzilla;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class WishlistActivity extends AppCompatActivity {

    ArrayList<Book> books = new ArrayList<>();
    ArrayList<String> booksString = new ArrayList<>();

    private ListView listView;
    private UserProfile currentPerson;

    public void removeBook(Book book){
        books.remove(book);
        booksString.remove(book.getTitle());
    }

    public void addBook(Book book){
        books.add(book);
        booksString.add(book.getTitle());
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_wishlist, booksString);

        //listView = (ListView) findViewById(R.id.imageView);
        //listView.setAdapter(adapter);

    }



}