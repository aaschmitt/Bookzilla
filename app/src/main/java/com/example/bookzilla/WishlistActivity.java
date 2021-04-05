package com.example.bookzilla;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class WishlistActivity extends AppCompatActivity {

    ArrayList<Book> books = new ArrayList<>();
    ArrayList<String> booksTitleString = new ArrayList<>();
    ArrayList<String> booksAuthorString = new ArrayList<>();

    int images[] = {R.drawable.book};

    private ListView listView;

    public void removeBook(Book book){
        books.remove(book);
        booksTitleString.remove(book.getTitle());
        booksAuthorString.remove(book.getAuthor());
    }

    public void addBook(Book book){
        books.add(book);
        booksTitleString.add(book.getTitle());
        booksAuthorString.add(book.getAuthor());
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        listView = findViewById(R.id.Wishlist);

        MyAdapter adapter = new MyAdapter(this, booksTitleString, booksAuthorString, images);

        listView.setAdapter(adapter);

        Book newBook = new Book("Book1", "Author1", "book1.com");

        addBook(newBook);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //if item is clicked then it should go to the book page
            }
        });

    }

    class MyAdapter extends ArrayAdapter<String>{
        Context context;

        ArrayList<String> bTitle;
        ArrayList<String> bAuthor;

        int bImages[];

        MyAdapter (Context c, ArrayList<String> title, ArrayList<String> author, int img[]){
            super(c, R.layout.wishlistimage, R.id.bookTitleView, title);
            this.context = c;
            this.bTitle = title;
            this.bAuthor = author;
            this.bImages = img;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row = layoutInflater.inflate(R.layout.wishlistimage, parent, false);
            ImageView images = row.findViewById(R.id.bookImages);
            TextView myTitle = row.findViewById(R.id.bookTitleView);
            TextView myAuthor = row.findViewById(R.id.bookAuthorView);

            images.setImageResource(bImages[position]);
            myTitle.setText(bTitle.get(position));
            myAuthor.setText(bAuthor.get(position));

            return row;
        }
    }



}