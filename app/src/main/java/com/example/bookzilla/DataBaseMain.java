package com.example.bookzilla;

import android.app.Notification;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class DataBaseMain  extends AppCompatActivity {

    TextView textView;


    String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_view);

        textView = (TextView) findViewById(R.id.textView);


        DatabaseHelper db = new DatabaseHelper(this);

        //inserting books
        db.addBook(new Book("book1","author1", "ldsakjflk"));
        db.addBook(new Book("book2","author2", "ldsakjflk"));
        db.addBook(new Book("book3","author3", "ldsakjflk"));

        //diplay the books
        List<Book> books = db.getAllBooks();

        for (Book b: books){
            String log = "Title: " + b.getTitle() + ", Author: " + b.getAuthor() + ", Link: " +  b.getCurrentStatus() + "\n";

            text = text + log;
        }

        textView.setText(text);
    }



}
