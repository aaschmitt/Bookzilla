package com.example.bookzilla;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class UserProfilePageActivity extends AppCompatActivity {

    private ListView listView;
    private TextView textView;

    private final String CURRENTUSERSTR = "CURRENT USER: ";
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_page);

        ConfigureDisplay();
    }

    private void ConfigureDisplay() {
        // Update list of books
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                CurrentUserProfile.profile.getBookTitles()
        );
        listView.setAdapter(adapter);
        UpdateCurrentUserTextView();
    }

    private void UpdateCurrentUserTextView() {
        textView = (TextView) findViewById(R.id.currentUserTextView);
        textView.setText(CURRENTUSERSTR + CurrentUserProfile.profile.getName());
    }

    public void OnChangeCurrentUserButtonClick(View view) {
        Intent intent = new Intent(this, ChooseExistingUserProfileActivity.class);
        startActivity(intent);
    }


    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        MenuItem menuItem =menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        }); {

        }
        return super.onCreateOptionsMenu(menu);
    }
     */

}
