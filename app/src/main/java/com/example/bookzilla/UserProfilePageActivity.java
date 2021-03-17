package com.example.bookzilla;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class UserProfilePageActivity extends AppCompatActivity {

    private ListView listView;
    private UserProfile currentUserProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_page);

        //TODO -- delete this user and books, was created for testing purposes
        currentUserProfile = new UserProfile("Audrey", "audrey123");
        currentUserProfile.AddBook(new Book("Catcher in the Rye", "J.D. Salinger", "NA"));
        currentUserProfile.AddBook(new Book("The Little Prince", "Antoine de Saint-Exupery", "NA" ));
        currentUserProfile.AddBook(new Book("Dream of the Red Chamber", "Cao Xueqin", "NA"));
        currentUserProfile.AddBook(new Book("The Hobbit", "J.R.R. Tolkien", "NA"));
        currentUserProfile.AddBook(new Book("And Then There Were None", "Agatha Christie", "NA"));
        currentUserProfile.AddBook(new Book("Farenheit 451", "", "NA"));
        //TODO ------------------------------------------------------

        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                currentUserProfile.getBookTitles()
        );

        listView.setAdapter(adapter);
    }


}
