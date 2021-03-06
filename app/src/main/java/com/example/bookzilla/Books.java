package com.example.bookzilla;

import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.view.MenuItemCompat;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Books extends AppCompatActivity {

   ListView listView;
    ArrayList<String> stringArrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    Scanner scan;
    String title;
    String author;
    String titleAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
    listView = findViewById(R.id.lv_listView);


    //populates the list with books
        try {
            InputStream fIn = getApplicationContext().getResources().getAssets()
                    .open("BookList.txt", Context.MODE_WORLD_READABLE);

            BufferedReader input = new BufferedReader(new InputStreamReader(fIn));
            String line = "";
            while ((line = input.readLine()) != null) {
                stringArrayList.add(line);
            }
        }
        catch (Exception e) {
            e.getMessage();
        }



    adapter = new ArrayAdapter<>(Books.this, android.R.layout.simple_list_item_1, stringArrayList);
    listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), adapter.getItem(position), Toast.LENGTH_SHORT).show();
                    titleAuthor= stringArrayList.get(position);
                    String[] parts = titleAuthor.split(" ");
                String[] parts2 = titleAuthor.split(" ");

                    author = parts[parts.length-2]+" "+parts[parts.length-1];

                //int i = parts2.length;
                title = parts2[0]+" ";
                for (int j = 1; j < parts2.length-2; j++) {
                   String temptitle = parts2[j]+" ";
                    title = title.concat(temptitle);
                }


                Intent intent = new Intent(Books.this,BookViewActivity.class);
                intent.putExtra("keytitle",title);
                intent.putExtra("keyauthor",author);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(), adapter.getItem(position), Toast.LENGTH_SHORT).show();

            }
        });
    }

    //Creates the menu that the items are displayed in
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
